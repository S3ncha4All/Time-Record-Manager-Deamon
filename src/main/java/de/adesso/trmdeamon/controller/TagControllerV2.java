package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTags;
import de.adesso.trmdeamon.dto.tag.TagReadDto;
import de.adesso.trmdeamon.service.TagServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/tags")
@RequiredArgsConstructor
public class TagControllerV2 {

    private final TagServiceV2 service;

    @Operation(
            description = "Returns all Tags or from a Booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tags returned")
            }
    )
    @GetMapping()
    public ResponseEntity<Page<TagReadDto>> getPagedTags(
            @Valid @RequestParam(required = false) String filterName,
            @Valid @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Valid @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @Valid @RequestParam(required = false, defaultValue = "id") SortTags sortTags,
            @Valid @RequestParam(required = false, defaultValue = "asc") SortOrder sortOrder
    ) {
        return ResponseEntity.ok(service.getPagedTags(filterName, pageIndex, pageSize, sortTags, sortOrder));
    }
}
