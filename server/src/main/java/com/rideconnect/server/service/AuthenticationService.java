package com.rideconnect.server.service;

import com.rideconnect.server.dto.AuthenticationResponse;
import com.rideconnect.server.dto.LoginRequest;
import com.rideconnect.server.exception.BadRequestException;
import com.rideconnect.server.model.User;
import com.rideconnect.server.repository.UserRepository;
import com.rideconnect.server.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rideconnect.server.constant.ErrorMessage.EMAIL_NOT_FOUND;
import static com.rideconnect.server.constant.ErrorMessage.PASSWORD_INCORRECT;
import static com.rideconnect.server.constant.FieldName.EMAIL_FIELD;
import static com.rideconnect.server.constant.FieldName.PASSWORD_FIELD;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(LoginRequest request) throws BadRequestException {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(EMAIL_FIELD , EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException(PASSWORD_FIELD , PASSWORD_INCORRECT);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
