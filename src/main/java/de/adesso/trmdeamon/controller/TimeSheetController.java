package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.timesheet.ConstructTimeSheetDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.service.TimeSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time_sheet")
@RequiredArgsConstructor
public class TimeSheetController {

    private final TimeSheetService service;

    @Operation(
            description = "Create a new TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Time Sheet created")
            }
    )
    @PostMapping
    public ResponseEntity<TimeSheetDto> createTimeSheet(@Valid @RequestBody ConstructTimeSheetDto dto) {
        return ResponseEntity.status(201).body(service.createTimeSheet(dto));
    }

    @Operation(
            description = "Updates a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Time Sheet updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateTimeSheet(@Valid @PathVariable Long id, @Valid @RequestBody ConstructTimeSheetDto dto) {
        return ResponseEntity.ok(service.updateTimeSheet(id, dto));
    }

    @Operation(
            description = "Delete a TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Time Sheet deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSheet(@Valid @PathVariable Long id) {
        service.deleteTimeSheet(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Get a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Time Sheet fetched")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetDto> getTimeSheet(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getTimeSheet(id));
    }

    @Operation(
            description = "Get all TimeSheets",
            responses = {
                    @ApiResponse(responseCode = "200", description = "TimeSheets fetched")
            }
    )
    @GetMapping()
    public ResponseEntity<List<TimeSheetDto>> getAllTimeSheet() {
        return ResponseEntity.ok(service.getAllTimeSheets());
    }
}
