package com.rideconnect.server.controller;

import com.rideconnect.server.service.LocationIqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationIqService locationIqService;

    @GetMapping("/geocode")
    public ResponseEntity<String> geocode(@RequestParam String address) {
        String response = locationIqService.geocodeAddress(address);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reverse-geocode")
    public ResponseEntity<String> reverseGeocode(@RequestParam double lat, @RequestParam double lon) {
        String response = locationIqService.reverseGeocode(lat, lon);
        return ResponseEntity.ok(response);
    }
}

