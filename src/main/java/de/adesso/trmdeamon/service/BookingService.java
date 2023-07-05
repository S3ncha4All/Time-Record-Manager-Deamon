package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.bookings.BookingDto;
import de.adesso.trmdeamon.dto.bookings.ConstructBookingDto;
import de.adesso.trmdeamon.dto.bookings.UpdateBookingDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.Bucket;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.BookingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    public static final Mapper<Booking, ConstructBookingDto> constructionMapper = new Mapper<Booking, ConstructBookingDto>() {
        @Override
        public Booking fromDto(ConstructBookingDto dto) {
            return Booking.builder()
                    .name(dto.getName())
                    .build();
        }
    };

    public static final Mapper<Booking, BookingDto> bookingMapper = new Mapper<Booking, BookingDto>() {
        @Override
        public BookingDto fromEntity(Booking booking) {
            BookingDto b = BookingDto.builder()
                    .id(booking.getId())
                    .name(booking.getName())
                    .bucketId(booking.getBucket().getId())
                    .begin(booking.getBegin())
                    .build();
            return super.fromEntity(booking);
        }
    };

    private final BookingsRepository repository;
    private final BucketService bucketService;
    private final TimeSheetService timeSheetService;

    public TimeSheetDto createBooking(Long timeSheetId, Long bucketId, ConstructBookingDto dto) {
        Booking b = constructionMapper.fromDto(dto);
        Bucket bkt = bucketService.getBucket(bucketId);
        b.setBucket(bkt);
        preCheck(timeSheetId, bucketId, b);
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateBooking(Long timeSheetId, Long bucketId, Long id, UpdateBookingDto dto) {
        Booking b = getBooking(id);
        preCheck(timeSheetId, bucketId, b);
        if(dto.getEndBooking() != null && b.getEnd() != null) {
            b.setEnd(LocalDateTime.now());
        }
        if(dto.getName() != null) {
            b.setName(dto.getName());
        }
        if(dto.getBucketId() != null) {
            Bucket bkt = bucketService.getBucket(dto.getBucketId());
            b.setBucket(bkt);
        }
        repository.save(b);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto deleteBooking(Long timeSheetId, Long bucketId, Long id) {
        Booking b = getBooking(id);
        preCheck(timeSheetId, bucketId, b);
        repository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    private void preCheck(Long timeSheetId, Long bucketId, Booking b) {
        Bucket bkt = b.getBucket();
        TimeSheet ts = bkt.getTimeSheet();
        if(!timeSheetId.equals(ts.getId())) {
            throw new RuntimeException("Wrong TimeSheet-ID");
        }
        if(bucketId.equals(bkt.getId())) {
            throw new RuntimeException("Wrong Bucket-ID");
        }
    }

    private Booking getBooking(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Booking not found")
        );
    }
}
