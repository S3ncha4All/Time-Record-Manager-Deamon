package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {

    private final Mapper<TimeSheet, TimeSheetDto> mapper = new Mapper<>() {
        @Override
        public TimeSheetDto fromEntity(TimeSheet timeSheet) {
            return TimeSheetDto.builder()
                    .id(timeSheet.getId())
                    .name(timeSheet.getName())
                    .build();
        }

        @Override
        public TimeSheet fromDto(TimeSheetDto dto) {
            return TimeSheet.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .build();
        }
    };

    private final TimeSheetRepository repository;

    public TimeSheetDto createTimeSheet(TimeSheetDto dto) {
        if(dto.getId() != null) throw new RuntimeException("ID given");
        TimeSheet ts = mapper.fromDto(dto);
        return mapper.fromEntity(repository.save(ts));
    }

    public TimeSheetDto updateTimeSheet(TimeSheetDto dto) {
        if(dto.getId() == null) throw new RuntimeException("No ID given");
        TimeSheet ts = repository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        ts.setName(dto.getName());
        return mapper.fromEntity(repository.save(ts));
    }

    public TimeSheetDto getTimeSheet(Long id) {
        TimeSheet ts = repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        return mapper.fromEntity(ts);
    }

    public List<TimeSheetDto> getAllTimeSheets() {
        return mapper.listFromEntity(repository.findAll());
    }

    public void deleteTimeSheet(Long id) {
        repository.deleteById(id);
    }
}
