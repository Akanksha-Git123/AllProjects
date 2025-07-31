package com.payingguest.app.services;

import com.payingguest.app.dtos.PgListingRequestDTO;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;

public interface PgListingService {
    PgListing createPgListing(PgListingRequestDTO dto, User owner);
}
