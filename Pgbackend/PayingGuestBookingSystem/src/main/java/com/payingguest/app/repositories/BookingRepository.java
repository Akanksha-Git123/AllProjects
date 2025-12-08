package com.payingguest.app.repositories;

import com.payingguest.app.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // tenant user id is Long here; change to Integer if needed
    List<Booking> findByTenant_UserId(Long tenantId);
}
