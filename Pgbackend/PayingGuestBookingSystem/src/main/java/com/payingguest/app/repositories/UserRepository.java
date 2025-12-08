package com.payingguest.app.repositories;

import com.payingguest.app.entities.User;
import com.payingguest.app.enums.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by email (used for login)
    Optional<User> findByEmail(String email);

    // Check if a user already exists with the given email
    boolean existsByEmail(String email);

    // ✅ Check if a user already exists with the given phone number
    boolean existsByPhoneNumber(String phoneNumber);

    // ✅ Optionally find by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);
    
    List<User> findByUserType(UserType userType);
}
