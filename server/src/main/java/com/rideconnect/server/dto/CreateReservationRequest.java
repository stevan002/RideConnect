package com.rideconnect.server.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReservationRequest {

    @NotNull(message = "Ride ID cannot be null")
    private Long rideId;

    @Min(value = 1, message = "At least one seat must be reserved")
    private int numberOfSeatsReserved;
}
