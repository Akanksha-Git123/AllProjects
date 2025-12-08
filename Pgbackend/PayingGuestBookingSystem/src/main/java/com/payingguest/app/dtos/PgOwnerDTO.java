package com.payingguest.app.dtos;

public class PgOwnerDTO {
    private Integer userId;
    private String fullName;
    private String email;
    private String phoneNumber;

    // Constructor, getters, setters
    public PgOwnerDTO(Integer userId, String fullName, String email, String phoneNumber) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
