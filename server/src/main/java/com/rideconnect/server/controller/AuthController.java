package com.rideconnect.server.controller;

import com.rideconnect.server.dto.LoginRequest;
import com.rideconnect.server.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
