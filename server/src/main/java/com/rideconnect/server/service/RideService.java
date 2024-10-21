package com.rideconnect.server.service;

import com.rideconnect.server.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;

    public void createRide(){

    }
}
