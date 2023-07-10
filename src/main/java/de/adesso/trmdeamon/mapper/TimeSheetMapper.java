package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.timesheet.TimeSheetCreateDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDetailsDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.model.TimeSheet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeSheetMapper {

    private final BookingMapper bookingMapper;
    private final SettingsMapper settingMapper;

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
            dto.setBookings(bookingMapper.listToReadDetailsDto(ts.getBookings()));
        } else {
            dto.setBookings(new ArrayList<>());
        }
        if(ts.getSettings() != null) {
            dto.setSettings(settingMapper.listToReadDto(ts.getSettings()));
        } else {
            dto.setSettings(new ArrayList<>());
        }
        return dto;
    }

    public List<TimeSheetReadDto> listToReadDto(List<TimeSheet> list) {
        return list.stream().map(this::toReadDto).toList();
    }
}
