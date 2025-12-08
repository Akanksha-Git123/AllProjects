package com.payingguest.app.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO used by admin when creating a new amenity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequestDTO {

    @NotBlank(message = "Amenity name is required")
    private String name;
}
