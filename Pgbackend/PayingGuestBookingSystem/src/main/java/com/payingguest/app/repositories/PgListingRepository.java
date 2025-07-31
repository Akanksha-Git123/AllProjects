package com.payingguest.app.repositories;

import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgListingRepository extends JpaRepository<PgListing, Integer> {
    List<PgListing> findByOwner(User owner);
}
