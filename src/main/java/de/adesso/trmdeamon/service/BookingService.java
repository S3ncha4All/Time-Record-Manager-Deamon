package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.bookings.BookingDto;
import de.adesso.trmdeamon.dto.bookings.ConstructBookingDto;
import de.adesso.trmdeamon.dto.bookings.UpdateBookingDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.Bucket;
import de.adesso.trmdeamon.repository.BookingsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    public static final Mapper<Booking, ConstructBookingDto> ConstructionMapper = new Mapper<Booking, ConstructBookingDto>() {
        @Override
        public Booking fromDto(ConstructBookingDto dto) {
            return Booking.builder()
                    .name(dto.getName())
                    .build();
        }
    };

    public static final Mapper<Booking, BookingDto> BookingMapper = new Mapper<Booking, BookingDto>() {
        @Override
        public BookingDto fromEntity(Booking booking) {
            return BookingDto.builder()
                    .id(booking.getId())
                    .name(booking.getName())
                    .begin(booking.getBegin())
                    .end(booking.getEnd())
                    .attributes(AttributeService.BookingAttributeMapper.listFromEntity(booking.getBookingAttributes()))
                    .build();
        }
    };

    private final BookingsRepository repository;
    private final BucketService bucketService;
    private final TimeSheetService timeSheetService;

    public TimeSheetDto createBooking(Long timeSheetId, Long bucketId, ConstructBookingDto dto) {
        Booking b = ConstructionMapper.fromDto(dto);
        Bucket bkt = bucketService.getBucket(timeSheetId, bucketId);
        b.setBucket(bkt);
        preCheck(timeSheetId, bucketId, b);
        b.setBegin(LocalDateTime.now());
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateBooking(Long timeSheetId, Long bucketId, Long id, UpdateBookingDto dto) {
        Booking b = getBooking(timeSheetId, bucketId, id);
        if(dto.getEndBooking() != null && dto.getEndBooking() && b.getEnd() == null) {
            b.setEnd(LocalDateTime.now());
        }
        if(!StringUtils.isEmpty(dto.getName())) {
            b.setName(dto.getName());
        }
        if(dto.getBucketId() != null) {
            Bucket bkt = bucketService.getBucket(timeSheetId, dto.getBucketId());
            b.setBucket(bkt);
        }
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto deleteBooking(Long timeSheetId, Long bucketId, Long id) {
        Booking b = getBooking(timeSheetId, bucketId, id);
        repository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public void preCheck(Long timeSheetId, Long bucketId, Booking b) {
        Bucket bkt = b.getBucket();
        bucketService.preCheck(timeSheetId, bkt);
        if(!bucketId.equals(bkt.getId())) {
            throw new RuntimeException("Wrong Bucket-ID");
        }
    }

    public Booking getBooking(Long timeSheetId, Long bucketId, Long id) {
        Booking b = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Booking not found")
        );
        preCheck(timeSheetId, bucketId, b);
        return b;
    }
}
