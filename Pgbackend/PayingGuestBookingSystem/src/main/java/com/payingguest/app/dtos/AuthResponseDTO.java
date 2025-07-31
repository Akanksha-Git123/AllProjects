package com.payingguest.app.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private String userType;
    private boolean isActive;
    private String expirationTime;
    private PublicUserDTO user;
}
