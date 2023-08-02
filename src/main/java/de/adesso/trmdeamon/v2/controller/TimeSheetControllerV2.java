package de.adesso.trmdeamon.v2.controller;

import de.adesso.trmdeamon.v1.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.v2.service.TimeSheetServiceV2;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/time-sheet")
@RequiredArgsConstructor
public class TimeSheetControllerV2 {

    private final TimeSheetServiceV2 service;

    @Operation(
            description = "Get all TimeSheets paged",
            responses = {
                    @ApiResponse(responseCode = "200", description = "TimeSheets - Page returned")
            }
    )
    @GetMapping()
    public ResponseEntity<Page<TimeSheetReadDto>> getAllTimeSheet(
            @Valid @RequestParam(required = false) String filterName,
            @Valid @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Valid @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Valid @RequestParam(required = false, defaultValue = "id") SortTimeSheets sortTimeSheets,
            @Valid @RequestParam(required = false, defaultValue = "asc") SortOrder sortOrder
    ) {
        return ResponseEntity.ok(service.getPagedTimeSheets(filterName, pageIndex, pageSize, sortTimeSheets, sortOrder));
    }
}
