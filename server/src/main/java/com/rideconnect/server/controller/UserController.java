package com.rideconnect.server.controller;

import com.rideconnect.server.dto.RegisterRequest;
import com.rideconnect.server.repository.UserRepository;
import com.rideconnect.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){
        userService.registerUser(request);
        return ResponseEntity.ok("User successfully registered");
    }
}
