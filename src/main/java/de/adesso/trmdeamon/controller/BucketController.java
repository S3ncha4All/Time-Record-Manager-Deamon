package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.buckets.BucketDto;
import de.adesso.trmdeamon.dto.buckets.ConstructBucketDto;
import de.adesso.trmdeamon.dto.buckets.UpdateBucketDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.service.BucketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time_sheet/{timeSheetId}/buckets")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService service;

    @Operation(
            description = "Create a new Bucket in a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Bucket created")
            }
    )
    @PostMapping
    public ResponseEntity<TimeSheetDto> createBucket(@Valid @PathVariable Long timeSheetId, @Valid @RequestBody ConstructBucketDto dto) {
        return ResponseEntity.status(201).body(service.createBucket(timeSheetId, dto));
    }

    @Operation(
            description = "Updates a given Bucket in a given TimeSheet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TimeSheetDto> updateBucket(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long id, @Valid @RequestBody UpdateBucketDto dto) {
        return ResponseEntity.ok(service.updateBucket(timeSheetId, id, dto));
    }

    @Operation(
            description = "Delete a Bucket",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Bucket deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<TimeSheetDto> deleteBucket(@Valid @PathVariable Long timeSheetId, @Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteBucket(timeSheetId, id));
    }

}
