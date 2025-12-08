package com.payingguest.app.serviceimpl;

import com.payingguest.app.dtos.AmenityResponseDTO;
import com.payingguest.app.dtos.PgListingResponseDTO;
import com.payingguest.app.entities.Amenity;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.enums.UserType;
import com.payingguest.app.exceptions.NotFoundException;
import com.payingguest.app.exceptions.UnauthorizedException;
import com.payingguest.app.repositories.AmenityRepository;
import com.payingguest.app.repositories.PgListingRepository;
import com.payingguest.app.services.PgOwnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PgOwnerServiceImpl implements PgOwnerService {

    private final PgListingRepository pgListingRepository;
    private final AmenityRepository amenityRepository;
    private final ModelMapper modelMapper;

    private PgListing getValidatedPgListing(Integer ownerId, Integer pgId) {
        PgListing pgListing = pgListingRepository.findByPgIdAndOwnerUserId(pgId, ownerId)
                .orElseThrow(() -> new NotFoundException("PG not found or not owned by this user"));

        if (!pgListing.getOwner().getUserType().equals(UserType.OWNER)) {
            throw new UnauthorizedException("Only PG owners can manage amenities");
        }
        return pgListing;
    }

    @Override
    public PgListingResponseDTO assignAmenitiesToPg(Integer ownerId, Integer pgId, List<Integer> amenityIds) {
        PgListing pgListing = getValidatedPgListing(ownerId, pgId);

        List<Amenity> amenities = amenityRepository.findAllById(amenityIds)
                .stream()
                .filter(a -> a.getDeletedAt() == null)
                .collect(Collectors.toList());

        pgListing.setAmenities(amenities);
        return modelMapper.map(pgListingRepository.save(pgListing), PgListingResponseDTO.class);
    }

    @Override
    public PgListingResponseDTO addAmenitiesToPg(Integer ownerId, Integer pgId, List<Integer> amenityIds) {
        PgListing pgListing = getValidatedPgListing(ownerId, pgId);

        List<Amenity> current = pgListing.getAmenities();
        List<Amenity> newAmenities = amenityRepository.findAllById(amenityIds)
                .stream()
                .filter(a -> a.getDeletedAt() == null && !current.contains(a))
                .collect(Collectors.toList());

        current.addAll(newAmenities);
        return modelMapper.map(pgListingRepository.save(pgListing), PgListingResponseDTO.class);
    }

    @Override
    public PgListingResponseDTO removeAmenitiesFromPg(Integer ownerId, Integer pgId, List<Integer> amenityIds) {
        PgListing pgListing = getValidatedPgListing(ownerId, pgId);

        // Remove amenities by filtering out those with matching amenityIds
        List<Amenity> updatedAmenities = pgListing.getAmenities()
                .stream()
                .filter(a -> !amenityIds.contains(a.getAmenityId()))
                .collect(Collectors.toList());

        pgListing.setAmenities(updatedAmenities);
        pgListingRepository.save(pgListing);

        return modelMapper.map(pgListing, PgListingResponseDTO.class);
    }


    @Override
    public List<AmenityResponseDTO> getPgAmenities(Integer ownerId, Integer pgId) {
        PgListing pgListing = getValidatedPgListing(ownerId, pgId);

        return pgListing.getAmenities()
                .stream()
                .filter(a -> a.getDeletedAt() == null)
                .map(a -> modelMapper.map(a, AmenityResponseDTO.class))
                .collect(Collectors.toList());
    }
}
