package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.bookingtags.BookingTagsDto;
import de.adesso.trmdeamon.service.BookingTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking/{id}/tags")
@RequiredArgsConstructor
public class BookingTasksController {

    private final BookingTagService service;

    @Operation(
            description = "Add Tags to a given Booking",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tags added")
            }
    )
    @PostMapping
    public ResponseEntity<Void> addTagsToBooking(@Valid @PathVariable Long id, @Valid @RequestBody BookingTagsDto dto) {
        service.addTagsToBooking(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Remove Tags from a given Booking",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tags removed")
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> removeTagsFromBooking(@Valid @PathVariable Long id, @Valid @RequestBody BookingTagsDto dto) {
        service.removeTagsFromBooking(id, dto);
        return ResponseEntity.noContent().build();
    }
}
