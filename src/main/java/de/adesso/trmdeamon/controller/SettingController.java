package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.SettingDto;
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
            description = "Create a new Setting",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Setting created")
            }
    )
    @PostMapping
    public ResponseEntity<SettingDto> createSetting(@Valid @RequestBody SettingDto dto) {
        return ResponseEntity.status(201).body(service.createSetting(dto));
    }

    @Operation(
            description = "Updates a given Setting",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Setting updated")
            }
    )
    @PutMapping
    public ResponseEntity<SettingDto> updateSetting(@Valid @RequestBody SettingDto dto) {
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

    @Operation(
            description = "Get a given Setting",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Setting fetched")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SettingDto> getSetting(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getSetting(id));
    }

    @Operation(
            description = "Get all Settings",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Settings fetched")
            }
    )
    @GetMapping()
    public ResponseEntity<List<SettingDto>> getAllSettings() {
        return ResponseEntity.ok(service.getAllSettings());
    }
}
