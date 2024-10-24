package com.rideconnect.server.controller;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateReservationRequest;
import com.rideconnect.server.repository.ReservationRepository;
import com.rideconnect.server.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationRequest request, Principal principal){
        ApiResponse response = reservationService.createReservation(request, principal.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-user")
    public ResponseEntity<?> getReservation(Principal principal){
        return ResponseEntity.ok(reservationService.getReservations(principal.getName()));
    }

}
