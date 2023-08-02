package de.adesso.trmdeamon.v2.controller;

import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.v1.dto.tag.TagReadDto;
import de.adesso.trmdeamon.v2.service.TagServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(required = false) String filterName,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "id") SortTimeSheets sortTimeSheets,
            @RequestParam(required = false, defaultValue = "asc") SortOrder sortOrder
    ) {
        return ResponseEntity.ok(service.getPagedTags(filterName, pageIndex, pageSize, sortTimeSheets, sortOrder));
    }
}
