package com.payingguest.app.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    // Generic response fields
    private int status;
    private String message;

    // Login-specific response
    private String token;
    private String userType; // "tenant", "owner", or "admin"
    private Boolean isActive;
    private String expirationTime;

    // Register-specific response
    private PublicUserDTO registeredUser;

    // User data
    private PublicUserDTO user;
    private List<PublicUserDTO> users;

//    // PG Listings
//    private PgListingDTO pg;
//    private List<PgListingDTO> pgs;
//
//    // Bookings
//    private BookingDTO booking;
//    private List<BookingDTO> bookings;
//
//    // Payments
//    private PaymentDTO payment;
//    private List<PaymentDTO> payments;
//
//    // Reviews
//    private ReviewDTO review;
//    private List<ReviewDTO> reviews;
//
//    // Notifications
//    private NotificationDTO notification;
//    private List<NotificationDTO> notifications;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
