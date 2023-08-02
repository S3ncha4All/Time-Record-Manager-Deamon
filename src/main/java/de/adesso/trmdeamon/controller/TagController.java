package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.tag.TagCreateDto;
import de.adesso.trmdeamon.dto.tag.TagReadDto;
import de.adesso.trmdeamon.dto.tag.TagUpdateDto;
import de.adesso.trmdeamon.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @Operation(
            description = "Create a new Tag",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tag created")
            }
    )
    @PostMapping("")
    public ResponseEntity<TagReadDto> createTag(@Valid @RequestBody TagCreateDto dto) {
        return ResponseEntity.status(201).body(service.createTag(dto));
    }

    @Operation(
            description = "Returns all Tags or from a Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tags returned")
            }
    )
    @GetMapping()
    public ResponseEntity<List<TagReadDto>> getTags() {
        return ResponseEntity.ok(service.getAllTags());
    }

    @Operation(
            description = "Updates a given Tag",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tag updated")
            }
    )
    @PutMapping()
    public ResponseEntity<TagReadDto> updateTag(@Valid @RequestBody TagUpdateDto dto) {
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
