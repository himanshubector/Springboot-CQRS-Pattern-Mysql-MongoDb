package com.example.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter  // Generates getters for all fields
@Builder // Generates a builder for creating instances
@AllArgsConstructor // Generates an all-arguments constructor
public class ApiErrorResponse {

    private final String message;         // Error message
    private final int statusCode;         // HTTP Status code
    private final String error;           // HTTP status name (e.g., NOT_FOUND)
    private final LocalDateTime timestamp; // Error occurrence timestamp


    // Automatically set timestamp during build if not explicitly set
    public static ApiErrorResponse.ApiErrorResponseBuilder builder() {
        return new ApiErrorResponseBuilder().timestamp(LocalDateTime.now());
    }
}

