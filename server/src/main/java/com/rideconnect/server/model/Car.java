package com.rideconnect.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Make cannot be empty")
    @Size(max = 50, message = "Make cannot exceed 50 characters")
    private String make;

    @Column(nullable = false)
    @NotBlank(message = "Model cannot be empty")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    @Column(nullable = false)
    @Min(value = 1886, message = "Year must be at least 1886")
    @Max(value = 2025, message = "Year must be a realistic future year")
    private int year;

    @Column
    @Pattern(
            regexp = "^[A-Z0-9 -]{1,10}$",
            message = "License plate must be between 1 and 10 characters and can contain uppercase letters, numbers, spaces, and hyphens"
    )
    private String licensePlate;
}

