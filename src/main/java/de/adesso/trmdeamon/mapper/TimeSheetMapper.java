package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.timesheet.TimeSheetCreateDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDetailsDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.model.TimeSheet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeSheetMapper {

    public TimeSheet fromCreateDto(TimeSheetCreateDto dto) {
        return TimeSheet.builder()
                .name(dto.getName())
                .build();
    }

    public TimeSheetReadDto toReadDto(TimeSheet ts) {
        TimeSheetReadDto dto = TimeSheetReadDto.builder()
                .id(ts.getId())
                .name(ts.getName())
                .build();
        if(ts.getBookings() != null) {
            dto.setBookingCount(ts.getBookings().size());
        } else {
            dto.setBookingCount(0);
        }
        return dto;
    }

    public TimeSheetReadDetailsDto toReadDetailsDto(TimeSheet ts) {
        TimeSheetReadDetailsDto dto = TimeSheetReadDetailsDto.builder()
                .id(ts.getId())
                .name(ts.getName())
                .build();
        if(ts.getBookings() != null) {
            //SET BOOKINGS
        }
        if(ts.getSettings() != null) {
            //SET SETTINGS
        }
        return dto;
    }

    public List<TimeSheetReadDto> listToReadDto(List<TimeSheet> list) {
        return list.stream().map(this::toReadDto).toList();
    }
}
