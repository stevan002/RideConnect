package com.rideconnect.server.service;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateRideRequest;
import com.rideconnect.server.exception.BadRequestException;
import com.rideconnect.server.model.Car;
import com.rideconnect.server.model.Ride;
import com.rideconnect.server.model.User;
import com.rideconnect.server.repository.CarRepository;
import com.rideconnect.server.repository.RideRepository;
import com.rideconnect.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.rideconnect.server.constant.ErrorMessage.*;
import static com.rideconnect.server.constant.FieldName.*;
import static com.rideconnect.server.mapper.RideMapper.RIDE_MAPPER;

@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ApiResponse createRide(CreateRideRequest request, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(EMAIL_FIELD, EMAIL_NOT_FOUND));

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new BadRequestException(CAR_FIELD, CAR_NOT_FOUND));

        Ride ride = RIDE_MAPPER.createRide(request);
        ride.setCar(car);
        ride.setUser(user);
        rideRepository.save(ride);
        return new ApiResponse("Created ride", true);
    }

    public ApiResponse deleteRide(Long rideId, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(EMAIL_FIELD, EMAIL_NOT_FOUND));

        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new BadRequestException(RIDE_FIELD, RIDE_NOT_FOUND));

        if(!ride.getUser().equals(user)){
            throw new BadRequestException(RIDE_FIELD, DELETE_DENIDE);
        }

        rideRepository.delete(ride);
        return new ApiResponse("Deleted ride", true);
    }

    public Ride getRide(Long id, String name) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(RIDE_FIELD, RIDE_NOT_FOUND));
    }
}
