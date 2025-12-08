package com.payingguest.app.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.payingguest.app.entities.PgImage;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.repositories.PgImageRepository;
import com.payingguest.app.repositories.PgListingRepository;

@Service
public class PgImageServiceImpl {

	@Autowired PgListingRepository pgListingRepository;
    @Autowired PgImageRepository pgImageRepository;
	
    public List<PgImage> uploadImages(Integer pgId, MultipartFile[] images) throws IOException {
        PgListing pg = pgListingRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        List<PgImage> savedImages = new ArrayList<>();

        for (MultipartFile file : images) {
            String contentType = file.getContentType();
            if (contentType == null ||
               !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
                throw new RuntimeException("Only JPG, JPEG, and PNG files are allowed");
            }

            PgImage pgImage = new PgImage();
            pgImage.setPg(pg);
            pgImage.setFileName(file.getOriginalFilename());
            pgImage.setImageData(file.getBytes());

            // This is the correct save call
            pgImageRepository.save(pgImage);
        }

        return savedImages;
    }
	
  
	
}
