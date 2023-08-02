package de.adesso.trmdeamon.v2.service;

import de.adesso.trmdeamon.v1.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceV2 {

    public Page<TimeSheetReadDto> getPagedTimeSheets(String filterTimeSheetName, Integer pageIndex, Integer pageSize, SortTimeSheets sortTimeSheets, SortOrder sortOrder) {
        return null;
    }
}
