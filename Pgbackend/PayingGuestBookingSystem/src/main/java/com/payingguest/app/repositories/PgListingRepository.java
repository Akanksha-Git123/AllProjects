package com.payingguest.app.repositories;

import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;
import com.payingguest.app.enums.GenderAllowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PgListingRepository extends JpaRepository<PgListing, Integer> {

    // ✅ Original methods
    List<PgListing> findByOwner(User owner);

    Optional<PgListing> findByPgIdAndOwnerUserId(Integer pgId, Integer ownerId);

    // ✅ New methods for public PG viewing (with soft-delete filter)
    List<PgListing> findByDeletedAtIsNull();

    List<PgListing> findByCityAndDeletedAtIsNull(String city);

    List<PgListing> findByGenderAllowedAndDeletedAtIsNull(GenderAllowed gender);

    @Query("SELECT p FROM PgListing p WHERE LOWER(p.city) = LOWER(:city) AND p.genderAllowed = :gender AND p.deletedAt IS NULL")
    List<PgListing> findByCityAndGenderAllowedIgnoreCase(@Param("city") String city, @Param("gender") GenderAllowed gender);
    
    List<PgListing> findAllByDeletedAtIsNull();

    
}
