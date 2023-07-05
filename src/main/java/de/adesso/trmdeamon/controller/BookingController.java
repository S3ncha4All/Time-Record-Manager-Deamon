package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.bookings.ConstructBookingDto;
import de.adesso.trmdeamon.dto.bookings.UpdateBookingDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time_sheet/{timeSheetId}/buckets/{bucketId}/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @Operation(
            description = "Create a new Booking in a given TimeSheet and a given Bucket",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Bucket created")
            }
    )
    @PostMapping
    public ResponseEntity<TimeSheetDto> createBooking(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @RequestBody ConstructBookingDto dto) {
        return ResponseEntity.status(201).body(service.createBooking(timeSheetId, bucketId, dto));
    }

    @Operation(
            description = "Updates a given Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateBooking(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long id, @Valid @RequestBody UpdateBookingDto dto) {
        return ResponseEntity.ok(service.updateBooking(timeSheetId, bucketId, id, dto));
    }

    @Operation(
            description = "Delete a given Bucket",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Bucket deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<TimeSheetDto> deleteBooking(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteBooking(timeSheetId, bucketId, id));
    }
}
