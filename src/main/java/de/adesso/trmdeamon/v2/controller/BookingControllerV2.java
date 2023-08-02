package de.adesso.trmdeamon.v2.controller;

import de.adesso.trmdeamon.util.sort.SortBookings;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.v1.dto.booking.BookingCreateDto;
import de.adesso.trmdeamon.v1.dto.booking.BookingReadDetailsDto;
import de.adesso.trmdeamon.v1.dto.booking.BookingReadDto;
import de.adesso.trmdeamon.v1.dto.booking.BookingUpdateDto;
import de.adesso.trmdeamon.v1.service.BookingService;
import de.adesso.trmdeamon.v2.service.BookingServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @Valid @RequestParam(value = "time-sheet-id", required = false) Long timeSheetId,
            @Valid @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Valid @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Valid @RequestParam(required = false, defaultValue = "id") SortBookings sortBookings,
            @Valid @RequestParam(required = false, defaultValue = "asc") SortOrder sortOrder
    ) {
        return ResponseEntity.ok(service.getPagedBooking(timeSheetId, pageIndex, pageSize, sortBookings, sortOrder));
    }
}
