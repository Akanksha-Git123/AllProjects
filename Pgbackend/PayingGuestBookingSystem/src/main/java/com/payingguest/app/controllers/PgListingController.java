package com.payingguest.app.controllers;

import com.payingguest.app.dtos.PgImageDto;
import com.payingguest.app.dtos.PgListingRequestDTO;
import com.payingguest.app.dtos.PgListingResponseDTO;
import com.payingguest.app.dtos.responses.ApiResponse;
import com.payingguest.app.dtos.responses.ResponseBuilder;
import com.payingguest.app.entities.PgImage;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;
import com.payingguest.app.repositories.PgImageRepository;
import com.payingguest.app.security.AuthUser;
import com.payingguest.app.serviceimpl.PgImageServiceImpl;
import com.payingguest.app.services.PgListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.payingguest.app.dtos.PgListingSummaryDTO;

@RestController
@RequestMapping("/api/pg-listings")
@RequiredArgsConstructor
public class PgListingController {

    private final PgListingService pgListingService;
    private final ModelMapper modelMapper;
    private final PgImageServiceImpl pgImageServiceImpl;
    private final PgImageRepository pgImageRepository;
 


    // ✅ Create a new PG listing (OWNER only)
    @PostMapping
    public ResponseEntity<ApiResponse<PgListingResponseDTO>> createPgListing(
            @Valid @RequestBody PgListingRequestDTO requestDTO,
            @AuthenticationPrincipal AuthUser authUser) {

        User owner = authUser.getUser(); // Gets current logged-in user

        PgListing createdPg = pgListingService.createPgListing(requestDTO, owner);

        PgListingResponseDTO responseDTO = modelMapper.map(createdPg, PgListingResponseDTO.class);

        return ResponseEntity
                .created(URI.create("/api/pg-listings/" + createdPg.getPgId()))
                .body(ResponseBuilder.success(responseDTO, "PG listing created successfully", 201));
    }
    
    @GetMapping("/pgsummary")
    public ResponseEntity<List<PgListingSummaryDTO>> getAllPgListingsWithImagesAndRating() {
        List<PgListingSummaryDTO> pgListings = pgListingService.getAllPgListingsWithImagesAndRating();
        return ResponseEntity.ok(pgListings);
    }
	
    
    @PostMapping("/upload/{pgId}")
    public ResponseEntity<List<PgImage>> uploadImages(
            @PathVariable Integer pgId,
            @RequestParam("images") MultipartFile[] images) throws IOException {
        return ResponseEntity.ok(pgImageServiceImpl.uploadImages(pgId, images));
    }
    
    @GetMapping("/pg/{pg_id}")
    public List<PgImageDto> getImagesByPgId(Integer pgId) {
    	System.err.println("---------------------"+pgId);
        List<PgImage> images = pgImageRepository.findImagesByPgIdNative(1);
    	System.err.println("---------------------"+images);

        return images.stream()
            .map(img -> new PgImageDto(img.getImageId(), img.getFileName()))
            .collect(Collectors.toList());
    }


   
  
}
