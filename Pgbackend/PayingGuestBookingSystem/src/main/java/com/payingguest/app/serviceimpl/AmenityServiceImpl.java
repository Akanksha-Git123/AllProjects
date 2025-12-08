package com.payingguest.app.serviceimpl;
import com.payingguest.app.dtos.AmenityRequestDTO;
import com.payingguest.app.dtos.AmenityResponseDTO;
import com.payingguest.app.entities.Amenity;
import com.payingguest.app.exceptions.NameValueRequiredException;
import com.payingguest.app.exceptions.ResourceAlreadyExistsException;
import com.payingguest.app.exceptions.NotFoundException;
import com.payingguest.app.repositories.AmenityRepository;
import com.payingguest.app.services.AmenityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;
    private final ModelMapper modelMapper;

    @Override
    public AmenityResponseDTO addAmenity(AmenityRequestDTO dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new NameValueRequiredException("Amenity name is required");
        }

        if (amenityRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new ResourceAlreadyExistsException("Amenity with name '" + dto.getName() + "' already exists");
        }

        Amenity amenity = Amenity.builder()
                .name(dto.getName().trim())
                .build();

        Amenity savedAmenity = amenityRepository.save(amenity);
        return modelMapper.map(savedAmenity, AmenityResponseDTO.class);
    }

    @Override
    public List<AmenityResponseDTO> getAllAmenities() {
        List<Amenity> amenities = amenityRepository.findByDeletedAtIsNull();
        return amenities.stream()
                .map(a -> modelMapper.map(a, AmenityResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void softDeleteAmenity(Integer amenityId) {
        Amenity amenity = amenityRepository.findByAmenityIdAndDeletedAtIsNull(amenityId)
                .orElseThrow(() -> new NotFoundException("Amenity with ID " + amenityId + " not found"));

        amenity.setDeletedAt(Timestamp.from(Instant.now()));
        amenityRepository.save(amenity);
    }
}

