package com.payingguest.app.repositories;

import com.payingguest.app.entities.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer> {

    Optional<Amenity> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

    List<Amenity> findByDeletedAtIsNull();

    Optional<Amenity> findByAmenityIdAndDeletedAtIsNull(Integer amenityId);
}
