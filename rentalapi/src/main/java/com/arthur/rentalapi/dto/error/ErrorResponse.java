package com.arthur.rentalapi.dto.error;

public record ErrorResponse(int status, String message, long timestamp) {}
