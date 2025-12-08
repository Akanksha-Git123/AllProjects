package com.payingguest.app.dtos;

import com.payingguest.app.enums.GenderAllowed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO to represent PG Listing details along with assigned amenities.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PgListingResponseDTO {

    private Integer id;
    private String pgName;
    private String description;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private GenderAllowed genderAllowed;
    private Integer totalRooms;

    private List<AmenityResponseDTO> amenities;
    //private List<ImageResponseDTO> pgImages;
    
}
