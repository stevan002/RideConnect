package com.rideconnect.server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    private String licensePlate;

    private boolean hasLuggageSpace;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

