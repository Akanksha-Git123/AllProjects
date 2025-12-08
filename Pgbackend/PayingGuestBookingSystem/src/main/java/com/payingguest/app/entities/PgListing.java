package com.payingguest.app.entities;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.payingguest.app.enums.GenderAllowed;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "pg_listings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PgListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pg_id")
    private Integer pgId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference("user-pgListings")  // Back ref to avoid infinite recursion
    private User owner;

    @Column(name = "pg_name")
    private String pgName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_allowed")
    private GenderAllowed genderAllowed;

    @Column(name = "total_rooms")
    private Integer totalRooms;

    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @OneToMany(mappedBy = "pg")
    @JsonManagedReference  // Forward ref for serialization
    private List<RoomType> roomTypes;

    @OneToMany(mappedBy = "pg")
    @JsonManagedReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "pg")
    @JsonManagedReference
    private List<Booking> bookings;

    @OneToMany(mappedBy = "pg")
    @JsonManagedReference
    private List<PgImage> pgImages;

    @ManyToMany
    @JoinTable(
        name = "pg_amenities",
        joinColumns = @JoinColumn(name = "pg_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;

}
