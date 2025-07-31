package com.payingguest.app.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.payingguest.app.enums.RoomCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomTypeId;

    @ManyToOne
    @JoinColumn(name = "pg_id", nullable = false)
    private PgListing pg;

    @Enumerated(EnumType.STRING)
    private RoomCategory roomCategory;

    private BigDecimal price;
    private int totalBeds;
    private int availableBeds;

    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    private Timestamp deletedAt;
}
