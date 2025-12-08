package com.payingguest.app.entities;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.payingguest.app.enums.UserType;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, columnDefinition = "TEXT")
    private String passwordHash;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "profile_picture", columnDefinition = "TEXT")
    private String profilePicture;

    @Column(name = "is_active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isActive;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("user-pgListings") // Forward ref: User → PgListing
    private List<PgListing> pgListings;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Back ref: User → Booking (skip to prevent recursion)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmailNotification> emailNotifications;
}
