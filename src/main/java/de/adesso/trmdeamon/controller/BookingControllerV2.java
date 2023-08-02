package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.util.sort.SortBookings;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.service.BookingServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/booking")
@RequiredArgsConstructor
public class BookingControllerV2 {

    private final BookingServiceV2 service;

    @Operation(
            description = "Returns a specific Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking updated")
            }
    )
    @GetMapping()
    public ResponseEntity<Page<BookingReadDto>> getAllBookings(
            @Valid @RequestParam(required = false, value = "time-sheet-id") Long timeSheetId,
            @Valid @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Valid @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Valid @RequestParam(required = false, defaultValue = "id") SortBookings sortBookings,
            @Valid @RequestParam(required = false, defaultValue = "asc") SortOrder sortOrder
    ) {
        return ResponseEntity.ok(service.getPagedBooking(timeSheetId, pageIndex, pageSize, sortBookings, sortOrder));
    }
}
