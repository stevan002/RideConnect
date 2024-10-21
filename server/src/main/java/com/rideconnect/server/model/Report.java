package com.rideconnect.server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reason;

    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
}

