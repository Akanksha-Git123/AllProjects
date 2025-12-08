package com.payingguest.app.services;

import com.payingguest.app.dtos.PgListingRequestDTO;
import com.payingguest.app.dtos.PgListingSummaryDTO;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;

import java.util.List;
import java.util.Map;

public interface PgListingService {

    // ✅ Existing method
    PgListing createPgListing(PgListingRequestDTO dto, User owner);
    List<PgListing> getAllPgListings();
    
    List<PgListingSummaryDTO> getAllPgListingsWithImagesAndRating();
	void deletePgListing(Integer pgId, User owner);


   
}
