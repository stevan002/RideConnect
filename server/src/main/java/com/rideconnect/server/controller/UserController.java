package com.rideconnect.server.controller;

import com.rideconnect.server.dto.RegisterRequest;
import com.rideconnect.server.repository.UserRepository;
import com.rideconnect.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> profileUser(Principal principal){
        return ResponseEntity.ok().body(userService.getProfile(principal.getName()));
    }
}
