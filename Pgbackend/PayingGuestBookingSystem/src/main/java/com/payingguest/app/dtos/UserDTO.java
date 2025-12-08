package com.payingguest.app.dtos;

public class UserDTO {
    private Integer userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String userType;

    // No-args constructor (needed for Jackson)
    public UserDTO() {}

    // All-args constructor
    public UserDTO(Integer userId, String fullName, String email, String phoneNumber, String userType) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    // Getters and setters for all fields
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}
