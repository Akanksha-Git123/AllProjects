package com.payingguest.app.entities;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.payingguest.app.enums.BookingStatus;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "pg_id")
    @JsonBackReference  // Back ref: Booking → PgListing (avoid cycle)
    private PgListing pg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonManagedReference  // Forward ref: Booking → User (tenant info included)
    private User tenant;

    private LocalDate moveInDate;
    private LocalDate moveOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    private Timestamp deletedAt;

}
