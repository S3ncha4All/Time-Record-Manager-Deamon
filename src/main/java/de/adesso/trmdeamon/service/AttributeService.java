package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.attributes.AttributesDto;
import de.adesso.trmdeamon.dto.attributes.ConstructAttributeDto;
import de.adesso.trmdeamon.dto.attributes.ConstructAttributesDto;
import de.adesso.trmdeamon.dto.attributes.UpdateAttributesDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.BookingAttribute;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.Bucket;
import de.adesso.trmdeamon.model.BucketAttribute;
import de.adesso.trmdeamon.repository.BookingAttributesRepository;
import de.adesso.trmdeamon.repository.BucketAttributesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {

    public static final Mapper<BookingAttribute, ConstructAttributeDto> BookingConstructionMapper = new Mapper<BookingAttribute, ConstructAttributeDto>() {
        @Override
        public BookingAttribute fromDto(ConstructAttributeDto dto) {
            return BookingAttribute.builder()
                    .name(dto.getName())
                    .value(dto.getValue())
                    .build();
        }
    };

    public static final Mapper<BucketAttribute, ConstructAttributeDto> BucketConstructionMapper = new Mapper<>() {
        @Override
        public BucketAttribute fromDto(ConstructAttributeDto dto) {
            return BucketAttribute.builder()
                    .name(dto.getName())
                    .value(dto.getValue())
                    .build();
        }
    };

    public static final Mapper<BookingAttribute, AttributesDto> BookingAttributeMapper = new Mapper<>() {
        @Override
        public AttributesDto fromEntity(BookingAttribute bookingAttribute) {
            return AttributesDto.builder()
                    .id(bookingAttribute.getId())
                    .name(bookingAttribute.getName())
                    .value(bookingAttribute.getValue())
                    .build();
        }
    };

    public static final Mapper<BucketAttribute, AttributesDto> BucketAttributeMapper = new Mapper<>() {
        @Override
        public AttributesDto fromEntity(BucketAttribute bucketAttribute) {
            return AttributesDto.builder()
                    .id(bucketAttribute.getId())
                    .name(bucketAttribute.getName())
                    .value(bucketAttribute.getValue())
                    .build();
        }
    };


    private final BucketService bucketService;
    private final BookingService bookingService;
    private final TimeSheetService timeSheetService;

    private final BookingAttributesRepository bookingAttributesRepository;
    private final BucketAttributesRepository bucketAttributesRepository;

    public TimeSheetDto createBookingAttributes(Long timeSheetId, Long bucketId, Long bookingId, ConstructAttributesDto dto) {
        List<BookingAttribute> bookingAttributes = BookingConstructionMapper.listFromDto(dto.getConstructAttributeDtos());
        Booking b = bookingService.getBooking(timeSheetId, bucketId, bookingId);
        bookingAttributes.forEach( ba -> ba.setBooking(b));
        bookingAttributesRepository.saveAll(bookingAttributes);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateBookingAttributes(Long timeSheetId, Long bucketId, Long bookingId, Long id, UpdateAttributesDto dto) {
        BookingAttribute a = getBookingAttribute(timeSheetId, bucketId, bookingId, id);
        if(!StringUtils.isEmpty(dto.getName()) && isBookingAttributeNameUnique(bookingId, dto.getName())) {
            a.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            a.setValue(dto.getValue());
        }
        bookingAttributesRepository.save(a);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto deleteBookingAttributes(Long timeSheetId, Long bucketId, Long bookingId, Long id) {
        bookingAttributesRepository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    private void preCheckBookingAttribute(Long timeSheetId, Long bucketId, Long bookingId, BookingAttribute a) {
        Booking b = a.getBooking();
        bookingService.preCheck(timeSheetId, bucketId, b);
        if(!bookingId.equals(b.getId())) {
            throw new RuntimeException("Wrong Booking-ID");
        }
    }

    private void preCheckBucketAttribute(Long timeSheetId, Long bucketId, BucketAttribute a) {
        Bucket b = a.getBucket();
        bucketService.preCheck(timeSheetId, b);
        if(!bucketId.equals(b.getId())) {
            throw new RuntimeException("Wrong Booking-ID");
        }
    }

    public BookingAttribute getBookingAttribute(Long timeSheetId, Long bucketId, Long bookingId, Long id) {
        BookingAttribute a =  bookingAttributesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Attribute not found")
        );
        preCheckBookingAttribute(timeSheetId, bucketId, bookingId, a);
        return a;
    }

    public BucketAttribute getBucketAttribute(Long timeSheetId, Long bucketId, Long id) {
        BucketAttribute a =  bucketAttributesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Attribute not found")
        );
        preCheckBucketAttribute(timeSheetId, bucketId, a);
        return a;
    }

    private boolean isBookingAttributeNameUnique(Long bookingId, String name) {
        // get all attributes for booking id
        //search names
        return true;
    }

    private boolean isBucketAttributeNameUnique(Long bookingId, String name) {
        // get all attributes for booking id
        //search names
        return true;
    }

    public TimeSheetDto createBucketAttributes(Long timeSheetId, Long id, ConstructAttributesDto dto) {
        List<BucketAttribute> bucketAttributes = BucketConstructionMapper.listFromDto(dto.getConstructAttributeDtos());
        Bucket b = bucketService.getBucket(timeSheetId, id);
        bucketAttributes.forEach( ba -> ba.setBucket(b));
        bucketAttributesRepository.saveAll(bucketAttributes);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateBucketAttributes(Long timeSheetId, Long bucketId, Long id, UpdateAttributesDto dto) {
        BucketAttribute a = getBucketAttribute(timeSheetId, bucketId, id);
        if(!StringUtils.isEmpty(dto.getName()) && isBucketAttributeNameUnique(bucketId, dto.getName())) {
            a.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            a.setValue(dto.getValue());
        }
        bucketAttributesRepository.save(a);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto deleteBucketAttributes(Long timeSheetId, Long bucketId, Long id) {
        bucketAttributesRepository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }
}
