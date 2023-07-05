package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.attributes.ConstructAttributesDto;
import de.adesso.trmdeamon.dto.attributes.UpdateAttributesDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time_sheet/{timeSheetId}/buckets/{bucketId}/booking/{bookingId}/attributes")
@RequiredArgsConstructor
public class AttributesController {

    private final AttributeService service;

    @Operation(
            description = "Create a new Attribute for a given Booking",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Attribute created")
            }
    )
    @PostMapping
    public ResponseEntity<TimeSheetDto> createBooking(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long bookingId, @Valid @RequestBody ConstructAttributesDto dto) {
        return ResponseEntity.status(201).body(service.createAttributes(timeSheetId, bucketId, bookingId, dto));
    }

    @Operation(
            description = "Updates a given Attribute",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Attribute updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateBooking(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long bookingId, @Valid @PathVariable Long id, @Valid @RequestBody UpdateAttributesDto dto) {
        return ResponseEntity.ok(service.updateAttributes(timeSheetId, bucketId, bookingId, id, dto));
    }

    @Operation(
            description = "Delete a given Attribute",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Attribute deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<TimeSheetDto> deleteBooking(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long bookingId, @Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteAttributes(timeSheetId, bucketId, bookingId, id));
    }
}
