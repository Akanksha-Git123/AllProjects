package com.payingguest.app.controllers;

import com.payingguest.app.dtos.PgListingRequestDTO;
import com.payingguest.app.dtos.PgListingResponseDTO;
import com.payingguest.app.dtos.responses.ApiResponse;
import com.payingguest.app.dtos.responses.ResponseBuilder;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.entities.User;
import com.payingguest.app.security.AuthUser;
import com.payingguest.app.services.PgListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/pg-listings")
@RequiredArgsConstructor
public class PgListingController {

    private final PgListingService pgListingService;
    private final ModelMapper modelMapper;

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
}
