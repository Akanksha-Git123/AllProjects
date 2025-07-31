package com.payingguest.app.entities;

import java.sql.Timestamp;
import java.util.List;

import com.payingguest.app.enums.GenderAllowed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pg_listings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PgListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pgId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String pgName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;

    private String city;
    private String state;
    private String pincode;

    @Enumerated(EnumType.STRING)
    private GenderAllowed genderAllowed;

    private int totalRooms;

    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    private Timestamp deletedAt;

    @OneToMany(mappedBy = "pg")
    private List<RoomType> roomTypes;

    @OneToMany(mappedBy = "pg")
    private List<Review> reviews;

    @OneToMany(mappedBy = "pg")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "pg")
    private List<PgImage> pgImages;

    @ManyToMany
    @JoinTable(
        name = "pg_amenities",
        joinColumns = @JoinColumn(name = "pg_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;
}


