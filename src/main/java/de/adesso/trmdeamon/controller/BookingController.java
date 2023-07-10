package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.booking.BookingCreateDto;
import de.adesso.trmdeamon.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.dto.booking.BookingUpdateDto;
import de.adesso.trmdeamon.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @Operation(
            description = "Create a new Booking",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Bucket created")
            }
    )
    @PostMapping
    public ResponseEntity<BookingReadDto> createBooking(@Valid @RequestBody BookingCreateDto dto) {
        return ResponseEntity.status(201).body(service.createBooking(dto));
    }

    @Operation(
            description = "Returns a specific Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookingReadDetailsDto> getBooking(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getBookingDto(id));
    }

    @Operation(
            description = "Returns a specific Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking updated")
            }
    )
    @GetMapping()
    public ResponseEntity<List<BookingReadDto>> getAllBookings(@Valid @RequestParam(value = "time-sheet-id") Long timeSheetId) {
        return ResponseEntity.ok(service.getAllBooking(timeSheetId));
    }

    @Operation(
            description = "Updates a given Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @PutMapping()
    public ResponseEntity<BookingReadDto> updateBooking(@Valid @RequestBody BookingUpdateDto dto) {
        return ResponseEntity.ok(service.updateBooking(dto));
    }

    @Operation(
            description = "End a given Booking",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Booking ended")
            }
    )
    @PutMapping("/{id}/end")
    public ResponseEntity<BookingReadDto> endBooking(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.endBooking(id));
    }

    @Operation(
            description = "Delete a given Booking",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Bucket deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@Valid @PathVariable Long id) {
        service.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
