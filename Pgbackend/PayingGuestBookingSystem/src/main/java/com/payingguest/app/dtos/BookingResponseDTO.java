package com.payingguest.app.dtos;

import com.payingguest.app.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class BookingResponseDTO {
    private Integer bookingId;

    // PG details
    private Integer pgId;
    private String pgName;
    private String address;
    private String city;

    // Owner details
    private String ownerName;
    private String ownerPhone;

    // Booking details
    private LocalDate moveInDate;
    private LocalDate moveOutDate;
    private BookingStatus status;

    // Room details
    private String roomType;
    private BigDecimal totalRent;

    private Timestamp createdAt;
}
