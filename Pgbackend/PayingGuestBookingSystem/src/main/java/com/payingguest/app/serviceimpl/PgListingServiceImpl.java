package com.payingguest.app.serviceimpl;

import com.payingguest.app.dtos.PgListingRequestDTO;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;
import com.payingguest.app.enums.UserType;
import com.payingguest.app.exceptions.UnauthorizedException;
import com.payingguest.app.repositories.PgListingRepository;
import com.payingguest.app.services.PgListingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PgListingServiceImpl implements PgListingService {

    private final PgListingRepository pgListingRepository;
    private final ModelMapper modelMapper;

    @Override
    public PgListing createPgListing(PgListingRequestDTO dto, User owner) {
        // Check if the user is an OWNER
        if (!owner.getUserType().equals(UserType.OWNER)) {
            throw new UnauthorizedException("Only PG owners can create listings.");
        }

        // Use ModelMapper to map the request DTO to the entity
        PgListing pgListing = modelMapper.map(dto, PgListing.class);

        // Set the owner manually (not in DTO)
        pgListing.setOwner(owner);

        return pgListingRepository.save(pgListing);
    }
}
