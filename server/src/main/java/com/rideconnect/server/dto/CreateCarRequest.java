package com.rideconnect.server.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCarRequest {

    @NotBlank(message = "Make cannot be empty")
    private String make;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotNull(message = "Year cannot be null")
    @Pattern(regexp = "^(18[8-9]\\d|19\\d{2}|20\\d{2}|2025)$", message = "Year must be between 1886 and 2025")
    private String year;

    @NotBlank(message = "License plate cannot be empty")
    @Pattern(regexp = "^[A-Z0-9-]{2,10}$", message = "License plate format is invalid")
    private String licencePlate;

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be a positive number")
    private Long userId;
}
