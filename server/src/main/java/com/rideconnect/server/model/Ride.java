package com.rideconnect.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int availableSeats;

    // Departure coordinates
    @Column(nullable = false)
    private double departureLat;

    @Column(nullable = false)
    private double departureLon;

    // Destination coordinates
    @Column(nullable = false)
    private double destinationLat;

    @Column(nullable = false)
    private double destinationLon;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}


