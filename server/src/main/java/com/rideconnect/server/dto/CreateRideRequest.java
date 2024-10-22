package com.rideconnect.server.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRideRequest {

    private String depature;
    private String destination;
    private LocalDateTime date;
    private int price;
    private int availableSeats;
    private double departureLat;
    private double departureLon;
    private double destinationLat;
    private double destinationLon;
    private Long userId;
    private Long carId;
}
