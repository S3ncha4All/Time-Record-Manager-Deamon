package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.timesheet.ConstructTimeSheetDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {

    public static final Mapper<TimeSheet, TimeSheetDto> mapper = new Mapper<>() {
        @Override
        public TimeSheetDto fromEntity(TimeSheet timeSheet) {
            return TimeSheetDto.builder()
                    .id(timeSheet.getId())
                    .name(timeSheet.getName())
                    .settings(SettingService.settingSettingMapper.listFromEntity(timeSheet.getSettings()))
                    .build();
        }
    };

    private final TimeSheetRepository repository;

    public TimeSheetDto createTimeSheet(ConstructTimeSheetDto dto) {
        TimeSheet ts = TimeSheet.builder()
                .name(dto.getName())
                .build();
        return mapper.fromEntity(repository.save(ts));
    }

    public TimeSheetDto updateTimeSheet(Long id, ConstructTimeSheetDto dto) {
        TimeSheet ts = repository.findById(id).orElseThrow(
                () -> new RuntimeException("TimeSheet not found")
        );
        ts.setName(dto.getName());
        return mapper.fromEntity(repository.save(ts));
    }

    public TimeSheetDto getTimeSheetDto(Long id) {
        TimeSheet ts =getTimeSheet(id);
        return mapper.fromEntity(ts);
    }

    public TimeSheet getTimeSheet(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("TimeSheet not found")
        );
    }

    public List<TimeSheetDto> getAllTimeSheets() {
        return mapper.listFromEntity(repository.findAll());
    }

    public void deleteTimeSheet(Long id) {
        repository.deleteById(id);
    }
}
