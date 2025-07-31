package com.payingguest.app.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    private String token;
    private String userType;
    private boolean isActive;
    private String expirationTime;
}
