package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.attributes.ConstructAttributesDto;
import de.adesso.trmdeamon.dto.attributes.UpdateAttributesDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time_sheet/{timeSheetId}/buckets/{bucketId}/attributes")
@RequiredArgsConstructor
public class BucketAttributesController {

    private final AttributeService service;

    @Operation(
            description = "Create a new Attribute for a given Bucket",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Attribute created")
            }
    )
    @PostMapping
    public ResponseEntity<TimeSheetDto> createBucketAttribute(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @RequestBody ConstructAttributesDto dto) {
        return ResponseEntity.status(201).body(service.createBucketAttributes(timeSheetId, bucketId, dto));
    }

    @Operation(
            description = "Updates a given Bucket-Attribute",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Attribute updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateBucketAttribute(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long id, @Valid @RequestBody UpdateAttributesDto dto) {
        return ResponseEntity.ok(service.updateBucketAttributes(timeSheetId, bucketId, id, dto));
    }

    @Operation(
            description = "Delete a given Bucket-Attribute",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Attribute deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<TimeSheetDto> deleteBucketAttribute(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long bucketId, @Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteBucketAttributes(timeSheetId, bucketId, id));
    }
}
