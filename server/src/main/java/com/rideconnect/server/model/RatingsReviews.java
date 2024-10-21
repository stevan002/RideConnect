package com.rideconnect.server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ratings_reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RatingsReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rating;

    private String review;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User reviewedUser;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;
}


