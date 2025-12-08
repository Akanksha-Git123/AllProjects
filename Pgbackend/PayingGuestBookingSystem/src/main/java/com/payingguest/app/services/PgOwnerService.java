package com.payingguest.app.services;

import com.payingguest.app.dtos.AmenityResponseDTO;
import com.payingguest.app.dtos.PgListingResponseDTO;

import java.util.List;

public interface PgOwnerService {

    // Replace all amenities with new ones (PUT)
    PgListingResponseDTO assignAmenitiesToPg(Integer ownerId, Integer pgId, List<Integer> amenityIds);

    // Add new amenities without replacing existing ones (PATCH)
    PgListingResponseDTO addAmenitiesToPg(Integer ownerId, Integer pgId, List<Integer> amenityIds);

    // Remove selected amenities from PG (DELETE)
    PgListingResponseDTO removeAmenitiesFromPg(Integer ownerId, Integer pgId, List<Integer> amenityIds);

    // Fetch all amenities assigned to a PG (GET)
    List<AmenityResponseDTO> getPgAmenities(Integer ownerId, Integer pgId);
}
