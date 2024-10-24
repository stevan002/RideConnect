package com.rideconnect.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_sender_id", nullable = false)
    private User userSender;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User userReceiver;

    private String content;

    private LocalDateTime timestamp;
}

