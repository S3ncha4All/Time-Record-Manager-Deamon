package de.adesso.trmdeamon.v2.controller;

import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortSettings;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.v1.dto.setting.SettingReadDto;
import de.adesso.trmdeamon.v2.service.SettingServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/settings")
@RequiredArgsConstructor
public class SettingControllerV2 {

    private final SettingServiceV2 service;

    @Operation(
            description = "Returns all Settings or all Settings for a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Settings returned")
            }
    )
    @GetMapping()
    public ResponseEntity<Page<SettingReadDto>> getAllSettings(
            @Valid @RequestParam(value = "time-sheet-id", required = false) Long timeSheetId,
            @Valid @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Valid @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Valid @RequestParam(required = false, defaultValue = "id") SortSettings sortSettings,
            @Valid @RequestParam(required = false, defaultValue = "asc") SortOrder sortOrder
    ) {
        return ResponseEntity.ok(service.getPagedSettings(timeSheetId, pageIndex, pageSize, sortSettings, sortOrder));
    }
}
