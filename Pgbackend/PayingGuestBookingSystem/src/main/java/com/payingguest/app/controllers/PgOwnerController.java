package com.payingguest.app.controllers;

import com.payingguest.app.dtos.AmenityResponseDTO;
import com.payingguest.app.dtos.PgListingResponseDTO;
import com.payingguest.app.dtos.PgOwnerDTO;
import com.payingguest.app.entities.User;
import com.payingguest.app.enums.UserType;
import com.payingguest.app.exceptions.UnauthorizedException;
import com.payingguest.app.repositories.UserRepository;
import com.payingguest.app.security.AuthUser;
import com.payingguest.app.services.PgOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owner/pgs")
@RequiredArgsConstructor
public class PgOwnerController {

    private final PgOwnerService pgOwnerService;
    private final UserRepository userRepository; // ✅ Injected repository

    @GetMapping
    public List<PgOwnerDTO> getAllPgOwners() {
        List<User> owners = userRepository.findByUserType(UserType.PG_OWNER);
        return owners.stream()
            .map(o -> new PgOwnerDTO(o.getUserId(), o.getFullName(), o.getEmail(), o.getPhoneNumber()))
            .collect(Collectors.toList());
    }

    // PUT: Replace amenities
    @PutMapping("/{pgId}/amenities")
    public ResponseEntity<PgListingResponseDTO> assignAmenitiesToPg(
            @PathVariable Integer pgId,
            @RequestBody List<Integer> amenityIds,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        validateOwner(authUser);
        PgListingResponseDTO response =
                pgOwnerService.assignAmenitiesToPg(authUser.getUser().getUserId(), pgId, amenityIds);
        return ResponseEntity.ok(response);
    }

    // PATCH: Add amenities
    @PatchMapping("/{pgId}/amenities")
    public ResponseEntity<PgListingResponseDTO> addAmenitiesToPg(
            @PathVariable Integer pgId,
            @RequestBody List<Integer> amenityIds,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        validateOwner(authUser);
        PgListingResponseDTO response =
                pgOwnerService.addAmenitiesToPg(authUser.getUser().getUserId(), pgId, amenityIds);
        return ResponseEntity.ok(response);
    }

    // DELETE: Remove amenities
    @DeleteMapping("/{pgId}/amenities")
    public ResponseEntity<PgListingResponseDTO> removeAmenitiesFromPg(
            @PathVariable Integer pgId,
            @RequestBody List<Integer> amenityIds,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        validateOwner(authUser);
        PgListingResponseDTO response =
                pgOwnerService.removeAmenitiesFromPg(authUser.getUser().getUserId(), pgId, amenityIds);
        return ResponseEntity.ok(response);
    }

    // GET: View all amenities for a PG
    @GetMapping("/{pgId}/amenities")
    public ResponseEntity<List<AmenityResponseDTO>> getPgAmenities(
            @PathVariable Integer pgId,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        validateOwner(authUser);
        List<AmenityResponseDTO> amenities =
                pgOwnerService.getPgAmenities(authUser.getUser().getUserId(), pgId);
        return ResponseEntity.ok(amenities);
    }

    // Helper to restrict access to PG_OWNER role
    private void validateOwner(AuthUser authUser) {
        User user = authUser.getUser();
        if (user.getUserType() != UserType.PG_OWNER) { // ✅ Use correct enum
            throw new UnauthorizedException("Access denied: only PG owners can perform this action.");
        }
    }
}
