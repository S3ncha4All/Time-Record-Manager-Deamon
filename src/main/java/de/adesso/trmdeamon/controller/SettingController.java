package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.settings.SettingCreateDto;
import de.adesso.trmdeamon.dto.settings.SettingReadDto;
import de.adesso.trmdeamon.dto.settings.SettingUpdateDto;
import de.adesso.trmdeamon.service.SettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
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
    public ResponseEntity<SettingReadDto> createSetting(@Valid @RequestBody SettingCreateDto dto) {
        return ResponseEntity.status(201).body(service.createSetting(dto));
    }

    @Operation(
            description = "Returns a given Setting in TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Setting returned")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SettingReadDto> getSetting(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getSettingDto(id));
    }

    @Operation(
            description = "Returns all Settings or all Settings for a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Settings returned")
            }
    )
    @GetMapping()
    public ResponseEntity<List<SettingReadDto>> getAllSettings(@Valid @RequestParam Long timeSheetId) {
        return ResponseEntity.ok(service.getAllSettings(timeSheetId));
    }

    @Operation(
            description = "Updates a given Setting in TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Setting updated")
            }
    )
    @PutMapping()
    public ResponseEntity<SettingReadDto> updateSetting(@Valid @RequestBody SettingUpdateDto dto) {
        return ResponseEntity.ok(service.updateSetting(dto));
    }

    @Operation(
            description = "Delete a Setting",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Setting deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@Valid @PathVariable Long id) {
        service.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}
