package com.payingguest.app.controllers;

import com.payingguest.app.dtos.AmenityRequestDTO;
import com.payingguest.app.dtos.AmenityResponseDTO;
import com.payingguest.app.dtos.responses.ApiResponse;
import com.payingguest.app.dtos.responses.ResponseBuilder;
import com.payingguest.app.services.AmenityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/amenities") // Amenity-specific base path under admin
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    // Only ADMIN can add amenity
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<AmenityResponseDTO>> addAmenity(
            @Valid @RequestBody AmenityRequestDTO requestDTO) {

        AmenityResponseDTO response = amenityService.addAmenity(requestDTO);
        return new ResponseEntity<>(
                ResponseBuilder.success(response, "Amenity added successfully", HttpStatus.CREATED.value()),
                HttpStatus.CREATED
        );
    }

    // Only ADMIN can get all amenities
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AmenityResponseDTO>>> getAllAmenities() {
        List<AmenityResponseDTO> list = amenityService.getAllAmenities();
        return ResponseEntity.ok(ResponseBuilder.success(list, "Amenities fetched successfully"));
    }

    // Only ADMIN can delete amenity
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{amenityId}")
    public ResponseEntity<ApiResponse<Void>> deleteAmenity(@PathVariable Integer amenityId) {
        amenityService.softDeleteAmenity(amenityId);
        return ResponseEntity.ok(ResponseBuilder.success(null, "Amenity deleted successfully"));
    }
}
