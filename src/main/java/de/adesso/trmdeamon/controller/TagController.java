package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.TagDto;
import de.adesso.trmdeamon.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @Operation(
            description = "Create a new Tag added to a Booking",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tag created")
            }
    )
    @PostMapping("")
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto dto) {
        return ResponseEntity.status(201).body(service.createTag(dto));
    }

    @Operation(
            description = "Returns all Tags from a Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tags returned")
            }
    )
    @GetMapping()
    public ResponseEntity<List<TagDto>> getTags(@Valid @RequestParam Long bookingId) {
        return ResponseEntity.ok(service.getAllTags(bookingId));
    }

    @Operation(
            description = "Updates a given Tag in a given Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tag updated")
            }
    )
    @PutMapping()
    public ResponseEntity<TagDto> updateTag(@Valid @RequestBody TagDto dto) {
        return ResponseEntity.ok(service.updateTag(dto));
    }

    @Operation(
            description = "Delete a Tag",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tag deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@Valid @PathVariable Long id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
