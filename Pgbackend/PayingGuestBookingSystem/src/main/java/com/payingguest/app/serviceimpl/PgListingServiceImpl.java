package com.payingguest.app.serviceimpl;

import com.payingguest.app.dtos.PgListingRequestDTO;
import com.payingguest.app.dtos.PgListingSummaryDTO;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;
import com.payingguest.app.enums.GenderAllowed;
import com.payingguest.app.enums.UserType;
import com.payingguest.app.exceptions.NotFoundException;
import com.payingguest.app.exceptions.UnauthorizedException;
import com.payingguest.app.repositories.PgImageRepository;
import com.payingguest.app.repositories.PgListingRepository;
//import com.payingguest.app.repositories.ReviewRepository;
import com.payingguest.app.services.PgListingService;
//import com.payingguest.app.utils.EnumUtils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PgListingServiceImpl implements PgListingService {

    private final PgListingRepository pgListingRepository;
    private final ModelMapper modelMapper;
  //  private final ReviewRepository reviewRepository;
    private final PgImageRepository pgImageRepository;

    /**
     * Creates a new PG listing by an owner.
     * Validates the user type before proceeding.
     */
    @Override
    public PgListing createPgListing(PgListingRequestDTO dto, User owner) {
        if (!owner.getUserType().equals(UserType.OWNER)) {
            throw new UnauthorizedException("Only PG owners can create listings.");
        }

        // Map the DTO to an entity
        PgListing pgListing = modelMapper.map(dto, PgListing.class);

        // Set owner info
        pgListing.setOwner(owner);

        return pgListingRepository.save(pgListing);
    }
    
    @Override
    public List<PgListing> getAllPgListings() {
        return pgListingRepository.findByDeletedAtIsNull(); // Only active PGs
    }
    
    
//    @Override
//    public List<PgListingSummaryDTO> getAllPgListingsWithImagesAndRating() {
//        return pgListingRepository.findAllByDeletedAtIsNull().stream().map(pg -> {
//            PgListingSummaryDTO dto = new PgListingSummaryDTO();
//            dto.setPgId(pg.getPgId());
//            dto.setName(pg.getPgName());
//            dto.setAddress(pg.getAddress());
//            dto.setCity(pg.getCity());
//
//            dto.setImages(
//                pg.getPgImages().stream()
//                    .filter(img -> img.getDeletedAt() == null)
//                    .map(img -> img.getImageUrl())
//                    .toList()
//            );
//
//         //   Double avgRating = reviewRepository.findAverageRatingByPgId(pg.getPgId());
//          //  dto.setAverageRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : null);
//
//            return dto;
//        }).toList();
//    }
    public List<PgListingSummaryDTO> getAllPgListingsWithImagesAndRating() {
        List<PgListing> listings = pgListingRepository.findAll();

        return listings.stream().map(pg -> {
            PgListingSummaryDTO dto = new PgListingSummaryDTO();
            dto.setPgId(pg.getPgId());
            dto.setName(pg.getPgName());
            dto.setAddress(pg.getAddress());
            dto.setCity(pg.getCity());

            // ✅ Fetch image URLs instead of image IDs
     //       dto.setImages(pgImageRepository.findImageUrlsByPgId(pg.getPgId()));

            dto.setAverageRating(4.5); // placeholder until rating logic is added
            return dto;
        }).toList();
    }

	@Override
	public void deletePgListing(Integer pgId, User owner) {
		// TODO Auto-generated method stub
		
	}
}
