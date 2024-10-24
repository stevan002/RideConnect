package com.rideconnect.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Departure location cannot be empty")
    private String departure;

    @Column(nullable = false)
    @NotBlank(message = "Destination location cannot be empty")
    private String destination;

    @Column(nullable = false)
    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private LocalDateTime date;

    @Column(nullable = false)
    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    @Column(nullable = false)
    @Min(value = 1, message = "There must be at least one available seat")
    private int availableSeats;

    @Column(nullable = false)
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private double departureLat;

    @Column(nullable = false)
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private double departureLon;

    @Column(nullable = false)
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private double destinationLat;

    @Column(nullable = false)
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private double destinationLon;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}
