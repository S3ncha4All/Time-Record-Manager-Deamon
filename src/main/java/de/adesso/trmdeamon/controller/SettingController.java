package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.settings.ConstructSettingDto;
import de.adesso.trmdeamon.dto.settings.SettingDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time_sheet/{timeSheetId}/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService service;

    @Operation(
            description = "Create a new Setting in TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Setting created")
            }
    )
    @PostMapping
    public ResponseEntity<TimeSheetDto> createSetting(@Valid @PathVariable Long timeSheetId, @Valid @RequestBody ConstructSettingDto dto) {
        return ResponseEntity.status(201).body(service.createSetting(timeSheetId, dto));
    }

    @Operation(
            description = "Updates a given Setting in TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Setting updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateSetting(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long id, @Valid @RequestBody ConstructSettingDto dto) {
        return ResponseEntity.ok(service.updateSetting(timeSheetId, id, dto));
    }

    @Operation(
            description = "Delete a Setting",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Setting deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long id) {
        service.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}
