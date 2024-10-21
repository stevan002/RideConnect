package com.rideconnect.server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int numberOfSeatsReserved;
}
