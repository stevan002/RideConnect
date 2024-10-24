package com.rideconnect.server.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRideRequest {

    @NotBlank(message = "Departure location cannot be empty")
    private String departure;

    @NotBlank(message = "Destination location cannot be empty")
    private String destination;

    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private LocalDateTime date;

    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    @Min(value = 1, message = "There must be at least one available seat")
    private int availableSeats;

    @NotNull(message = "Departure latitude cannot be null")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private double departureLat;

    @NotNull(message = "Departure longitude cannot be null")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private double departureLon;

    @NotNull(message = "Destination latitude cannot be null")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private double destinationLat;

    @NotNull(message = "Destination longitude cannot be null")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private double destinationLon;

    @NotNull(message = "Car ID cannot be null")
    private Long carId;
}
