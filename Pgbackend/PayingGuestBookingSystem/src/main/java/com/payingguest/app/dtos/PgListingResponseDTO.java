package com.payingguest.app.dtos;

import com.payingguest.app.entities.PgListing;
import com.payingguest.app.enums.GenderAllowed;
import lombok.*;

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

    
}
