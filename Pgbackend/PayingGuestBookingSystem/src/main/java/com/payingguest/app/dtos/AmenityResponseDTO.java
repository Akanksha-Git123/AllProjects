package com.payingguest.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO to represent Amenity details returned to the frontend.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmenityResponseDTO {
    private Integer amenityId;
    private String name;
}
