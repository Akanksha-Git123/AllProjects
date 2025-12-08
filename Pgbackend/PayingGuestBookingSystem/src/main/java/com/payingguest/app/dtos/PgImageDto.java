package com.payingguest.app.dtos;

public class PgImageDto {
    private Integer imageId;
    private String fileName;
    // Add any other fields you want to expose (e.g., URL or metadata)

    public PgImageDto(Integer imageId, String fileName) {
        this.imageId = imageId;
        this.fileName = fileName;
    }

    // Getters and setters (or use Lombok @Data)
    public Integer getImageId() { return imageId; }
    public void setImageId(Integer imageId) { this.imageId = imageId; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
}
