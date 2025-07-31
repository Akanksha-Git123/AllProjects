package com.payingguest.app.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "pg_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PgImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @ManyToOne
    @JoinColumn(name = "pg_id", nullable = false)
    private PgListing pg;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = true)
    private RoomType roomType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageUrl;

    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp uploadedAt;

    private Timestamp deletedAt;
}
