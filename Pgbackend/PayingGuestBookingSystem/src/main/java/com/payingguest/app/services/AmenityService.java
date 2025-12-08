package com.payingguest.app.services;

import com.payingguest.app.dtos.AmenityRequestDTO;
import com.payingguest.app.dtos.AmenityResponseDTO;

import java.util.List;

public interface AmenityService {

    AmenityResponseDTO addAmenity(AmenityRequestDTO amenityRequestDTO);

    List<AmenityResponseDTO> getAllAmenities();

    void softDeleteAmenity(Integer amenityId);
}
