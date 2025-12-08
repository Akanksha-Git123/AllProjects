package com.payingguest.app.controllers;

import com.payingguest.app.dtos.PgListingSummaryDTO;
import com.payingguest.app.dtos.UserDTO;
import com.payingguest.app.dtos.UserOwnerDTO;
import com.payingguest.app.entities.User;
import com.payingguest.app.enums.UserType;
import com.payingguest.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payingguest.app.services.PgListingService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream()
            .map(u -> new UserDTO(
                u.getUserId(),
                u.getFullName(),
                u.getEmail(),
                u.getPhoneNumber(),
                u.getUserType().toString()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/pg-owners")
    public ResponseEntity<List<UserOwnerDTO>> getAllPgOwners() {
        List<User> owners = userRepository.findByUserType(UserType.OWNER);
        List<UserOwnerDTO> dtos = owners.stream()
            .map(o -> new UserOwnerDTO(o.getUserId(), o.getFullName(), o.getEmail(), o.getPhoneNumber()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    private final PgListingService pgListingService;

    @GetMapping("/pgsummary")
    public ResponseEntity<List<PgListingSummaryDTO>> getAllPgListingsSummary() {
        List<PgListingSummaryDTO> pgListings = pgListingService.getAllPgListingsWithImagesAndRating();
        return ResponseEntity.ok(pgListings);
    }

}
