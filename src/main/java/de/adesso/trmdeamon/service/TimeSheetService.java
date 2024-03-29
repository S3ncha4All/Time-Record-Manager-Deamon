package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.timesheet.TimeSheetCreateDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDetailsDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetUpdateDto;
import de.adesso.trmdeamon.mapper.TimeSheetMapper;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.TimeSheetRepository;
import de.adesso.trmdeamon.util.sort.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {

    private final TimeSheetMapper mapper;
    private final TimeSheetRepository repository;

    public TimeSheetReadDto createTimeSheet(TimeSheetCreateDto dto) {
        TimeSheet ts = mapper.fromCreateDto(dto);
        return mapper.toReadDto(repository.save(ts));
    }

    public TimeSheetReadDto updateTimeSheet(Long id, TimeSheetUpdateDto dto) {
        TimeSheet ts = repository.findById(id).orElseThrow(
                () -> new RuntimeException("TimeSheet not found")
        );
        ts.setName(dto.getName());
        return mapper.toReadDto(repository.save(ts));
    }

    public List<TimeSheetReadDto> getAllTimeSheets() {
        List<TimeSheet> list = new ArrayList<>();
        repository.findAll(Sort.by("id").ascending()).forEach(list::add);
        return mapper.listToReadDto(list);
    }

    public void deleteTimeSheet(Long id) {
        repository.deleteById(id);
    }

    public TimeSheetReadDetailsDto getTimeSheetDto(Long id) {
        return mapper.toReadDetailsDto(getTimeSheet(id));
    }

    public TimeSheet getTimeSheet(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("TimeSheet not found")
        );
    }

}
