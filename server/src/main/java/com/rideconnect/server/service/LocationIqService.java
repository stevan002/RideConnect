package com.rideconnect.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationIqService {

    private static final String GEOCODING_URL = "https://us1.locationiq.com/v1/search.php";
    private static final String REVERSE_GEOCODING_URL = "https://us1.locationiq.com/v1/reverse.php";

    @Value("${locationiq.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public String geocodeAddress(String address) {
        String url = GEOCODING_URL + "?key=" + apiKey + "&q=" + address + "&format=json";
        return restTemplate.getForObject(url, String.class);
    }

    public String reverseGeocode(double lat, double lon) {
        String url = REVERSE_GEOCODING_URL + "?key=" + apiKey + "&lat=" + lat + "&lon=" + lon + "&format=json";
        return restTemplate.getForObject(url, String.class);
    }
}

