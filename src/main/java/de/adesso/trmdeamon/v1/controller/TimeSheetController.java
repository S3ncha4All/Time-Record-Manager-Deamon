package de.adesso.trmdeamon.v1.controller;

import de.adesso.trmdeamon.v1.dto.timesheet.TimeSheetCreateDto;
import de.adesso.trmdeamon.v1.dto.timesheet.TimeSheetReadDto;
import de.adesso.trmdeamon.v1.dto.timesheet.TimeSheetReadDetailsDto;
import de.adesso.trmdeamon.v1.dto.timesheet.TimeSheetUpdateDto;
import de.adesso.trmdeamon.v1.service.TimeSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/time-sheet")
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
    public ResponseEntity<TimeSheetReadDto> createTimeSheet(@Valid @RequestBody TimeSheetCreateDto dto) {
        return ResponseEntity.status(201).body(service.createTimeSheet(dto));
    }

    @Operation(
            description = "Updates a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Time Sheet updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetReadDto> updateTimeSheet(@Valid @PathVariable Long id, @Valid @RequestBody TimeSheetUpdateDto dto) {
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
                    @ApiResponse(responseCode = "200", description = "Time Sheet returned")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetReadDetailsDto> getTimeSheet(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getTimeSheetDto(id));
    }

    @Operation(
            description = "Get all TimeSheets",
            responses = {
                    @ApiResponse(responseCode = "200", description = "TimeSheets returned")
            }
    )
    @GetMapping()
    public ResponseEntity<List<TimeSheetReadDto>> getAllTimeSheet() {
        return ResponseEntity.ok(service.getAllTimeSheets());
    }
}
