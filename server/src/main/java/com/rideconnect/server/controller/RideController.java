package com.rideconnect.server.controller;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateRideRequest;
import com.rideconnect.server.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ride")
public class RideController {

    private final RideService rideService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createRide(@RequestBody CreateRideRequest request, Principal principal){
        ApiResponse response = rideService.createRide(request, principal.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRide(@PathVariable("id") Long id, Principal principal){
        return ResponseEntity.ok(rideService.getRide(id, principal.getName()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteRide(@PathVariable Long id, Principal principal){
        ApiResponse response = rideService.deleteRide(id, principal.getName());
        return ResponseEntity.ok(response);
    }
}
