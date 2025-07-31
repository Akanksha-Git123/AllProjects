package com.payingguest.app.dtos;

import com.payingguest.app.enums.GenderAllowed;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PgListingRequestDTO {

    @NotBlank
    private String pgName;

    private String description;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String pincode;

    @NotNull
    private GenderAllowed genderAllowed;

    @NotNull
    private Integer totalRooms;
}
