package com.rideconnect.server.controller;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.ChangePasswordRequest;
import com.rideconnect.server.dto.RegisterRequest;
import com.rideconnect.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public static final String UPLOAD_DIR = "uploads/";

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterRequest request) {
        ApiResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> profileUser(Principal principal) {
        return ResponseEntity.ok().body(userService.getProfile(principal.getName()));
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest request, Principal principal) {
        ApiResponse response = userService.changePassword(request, principal.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload-profile-image")
    public ResponseEntity<ApiResponse> uploadProfileImage(
            Principal principal,
            @RequestParam("file") MultipartFile file) {
        try {
            String fileType = file.getContentType();
            assert fileType != null;
            if (!fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body(new ApiResponse("Invalid file type. Only JPEG and PNG are supported.", false));
            }

            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String filename = principal.getName() + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            String imageUrl = "/uploads/" + filename;
            userService.updateUserImage(principal.getName(), imageUrl);

            return ResponseEntity.ok(new ApiResponse("Profile image uploaded successfully.", true));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error saving file: " + e.getMessage(), false));
        }
    }

    @GetMapping("/profile/image/{filename}")
    public ResponseEntity<Resource> getProfileImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)  // ili MediaType.IMAGE_PNG, zavisno od tipa fajla
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
