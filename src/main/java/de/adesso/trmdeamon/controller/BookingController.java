package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.BookingDto;
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
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingDto dto) {
        return ResponseEntity.status(201).body(service.createBooking(dto));
    }

    @Operation(
            description = "Returns a specific Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBooking(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getBookingDto(id));
    }

    @Operation(
            description = "Returns a specific Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking updated")
            }
    )
    @GetMapping()
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(service.getAllBooking());
    }

    @Operation(
            description = "Updates a given Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @PutMapping()
    public ResponseEntity<BookingDto> updateBooking(@Valid @RequestBody BookingDto dto) {
        return ResponseEntity.ok(service.updateBooking(dto));
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
