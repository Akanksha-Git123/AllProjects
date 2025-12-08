package com.payingguest.app.dtos;

import lombok.Data;
import java.util.List;

@Data
public class PgListingSummaryDTO {
    private Integer pgId;
    private String name;
    private String address;
    private String city;
    private List<String> images;
    private Double averageRating; // ⭐️ new field
}
