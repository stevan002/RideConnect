package com.rideconnect.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bio;
    private String imageUrl;
}
