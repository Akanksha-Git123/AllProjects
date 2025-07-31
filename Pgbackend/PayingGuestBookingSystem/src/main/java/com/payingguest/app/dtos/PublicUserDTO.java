package com.payingguest.app.dtos;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicUserDTO {

    private Integer userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String gender;

    private String profilePictureUrl;

    // Optional: You may include other safe fields like location, bio, etc.
}
