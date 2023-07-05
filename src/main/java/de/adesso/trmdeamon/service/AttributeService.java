package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.attributes.AttributesDto;
import de.adesso.trmdeamon.dto.attributes.ConstructAttributeDto;
import de.adesso.trmdeamon.dto.attributes.ConstructAttributesDto;
import de.adesso.trmdeamon.dto.attributes.UpdateAttributesDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Attribute;
import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.repository.AttributesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {

    public static final Mapper<Attribute, ConstructAttributeDto> ConstructionMapper = new Mapper<Attribute, ConstructAttributeDto>() {
        @Override
        public Attribute fromDto(ConstructAttributeDto dto) {
            return Attribute.builder()
                    .name(dto.getName())
                    .value(dto.getValue())
                    .build();
        }
    };

    public static final Mapper<Attribute, AttributesDto> AttributeMapper = new Mapper<>() {
        @Override
        public AttributesDto fromEntity(Attribute attribute) {
            return AttributesDto.builder()
                    .id(attribute.getId())
                    .name(attribute.getName())
                    .value(attribute.getValue())
                    .build();
        }
    };


    private final BookingService bookingService;
    private final TimeSheetService timeSheetService;

    private final AttributesRepository repository;

    public TimeSheetDto createAttributes(Long timeSheetId, Long bucketId, Long bookingId, ConstructAttributesDto dto) {
        List<Attribute> attributes = ConstructionMapper.listFromDto(dto.getConstructAttributeDtos());

        return null;
    }

    public TimeSheetDto updateAttributes(Long timeSheetId, Long bucketId, Long bookingId, Long id, UpdateAttributesDto dto) {
        Attribute a = getAttribute(timeSheetId, bucketId, bookingId, id);
        if(!StringUtils.isEmpty(dto.getName()) && isAttributeNameUnique(bookingId, dto.getName())) {
            a.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            a.setValue(dto.getValue());
        }
        repository.save(a);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto deleteAttributes(Long timeSheetId, Long bucketId, Long bookingId, Long id) {
        repository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    private void preCheck(Long timeSheetId, Long bucketId, Long bookingId, Attribute a) {
        Booking b = a.getBooking();
        bookingService.preCheck(timeSheetId, bucketId, b);
        if(!bookingId.equals(b.getId())) {
            throw new RuntimeException("Wrong Booking-ID");
        }
    }

    public Attribute getAttribute(Long timeSheetId, Long bucketId, Long bookingId, Long id) {
        Attribute a =  repository.findById(id).orElseThrow(
                () -> new RuntimeException("Attribute not found")
        );
        preCheck(timeSheetId, bucketId, bookingId, a);
        return a;
    }

    private boolean isAttributeNameUnique(Long bookingId, String name) {
        // get all attributes for booking id
        //search names
        return true;
    }
}
