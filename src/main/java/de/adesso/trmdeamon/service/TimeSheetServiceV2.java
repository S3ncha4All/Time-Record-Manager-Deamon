package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.util.MakePageRequest;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.mapper.TimeSheetMapper;
import de.adesso.trmdeamon.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceV2 {

    private final TimeSheetMapper mapper;
    private final TimeSheetRepository repository;

    public Page<TimeSheetReadDto> getPagedTimeSheets(String filterTimeSheetName, Integer pageIndex, Integer pageSize, SortTimeSheets sortTimeSheets, SortOrder sortOrder) {
        Pageable pr = MakePageRequest.make(pageIndex, pageSize, sortTimeSheets, sortOrder);
        Page<TimeSheet> list;
        if(filterTimeSheetName != null && !filterTimeSheetName.isEmpty()) {
            list = repository.findAllTimeSheetsLikeName(filterTimeSheetName, pr);
        } else {
            list = repository.findAll(pr);
        }
        return mapper.pageToReadDto(list);
    }
}
