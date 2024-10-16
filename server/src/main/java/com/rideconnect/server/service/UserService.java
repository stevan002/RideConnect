package com.rideconnect.server.service;

import com.rideconnect.server.dto.ProfileResponse;
import com.rideconnect.server.dto.RegisterRequest;
import com.rideconnect.server.exception.BadRequestException;
import com.rideconnect.server.model.User;
import com.rideconnect.server.model.enumeration.UserRole;
import com.rideconnect.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rideconnect.server.constant.ErrorMessage.*;
import static com.rideconnect.server.constant.FieldName.*;
import static com.rideconnect.server.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest userRequest){
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new BadRequestException(EMAIL_FIELD , EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new BadRequestException(PHONE_NUMBER_FIELD, PHONE_NUMBER_ALREADY_EXISTS);
        }

        User user = USER_MAPPER.registerUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(UserRole.USER);

        userRepository.save(user);
    }

    public ProfileResponse getProfile(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(USER_FIELD , USER_NOT_FOUND));

        return USER_MAPPER.getProfile(user);
    }
}
