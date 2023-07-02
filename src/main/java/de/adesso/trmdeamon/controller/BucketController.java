package de.adesso.trmdeamon.controller;

import de.adesso.trmdeamon.dto.BucketDto;
import de.adesso.trmdeamon.service.BucketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService service;

    @Operation(
            description = "Create a new Bucket",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Bucket created")
            }
    )
    @PostMapping
    public ResponseEntity<BucketDto> createBucket(@Valid @RequestBody BucketDto dto) {
        return ResponseEntity.status(201).body(service.createBucket(dto));
    }

    @Operation(
            description = "Updates a given Bucket",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket updated")
            }
    )
    @PutMapping
    public ResponseEntity<BucketDto> updateBucket(@Valid @RequestBody BucketDto dto) {
        return ResponseEntity.ok(service.updateBucket(dto));
    }

    @Operation(
            description = "Delete a Bucket",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Bucket deleted")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBucket(@Valid @PathVariable Long id) {
        service.deleteBucket(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Get a given Bucket",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bucket fetched")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BucketDto> getBucket(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getBucket(id));
    }

    @Operation(
            description = "Get all Settings",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Settings fetched")
            }
    )
    @GetMapping()
    public ResponseEntity<List<BucketDto>> getAllBuckets() {
        return ResponseEntity.ok(service.getAllBuckets());
    }
}
