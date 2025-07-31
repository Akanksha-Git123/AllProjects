package com.payingguest.app.dtos.responses;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseBuilder {

    // KEEP this for default 200 OK
    public static <T> ApiResponse<T> success(T data, String message) {
        return success(data, message, HttpStatus.OK.value());
    }

    // ADD THIS METHOD for custom success status (e.g., 201)
    public static <T> ApiResponse<T> success(T data, String message, int statusCode) {
        return ApiResponse.<T>builder()
                .status(statusCode)
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    //KEEP this for error responses
    public static <T> ApiResponse<T> failure(String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }
}
