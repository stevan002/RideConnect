package com.rideconnect.server.service;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateReservationRequest;
import com.rideconnect.server.exception.BadRequestException;
import com.rideconnect.server.model.Reservation;
import com.rideconnect.server.model.Ride;
import com.rideconnect.server.model.User;
import com.rideconnect.server.repository.ReservationRepository;
import com.rideconnect.server.repository.RideRepository;
import com.rideconnect.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.rideconnect.server.constant.ErrorMessage.*;
import static com.rideconnect.server.constant.FieldName.*;
import static com.rideconnect.server.mapper.ReservationMapper.RESERVATION_MAPPER;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RideRepository rideRepository;

    public ApiResponse createReservation(CreateReservationRequest request, String email){

        Reservation reservation = RESERVATION_MAPPER.createReservation(request);
        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new BadRequestException(EMAIL_FIELD, EMAIL_NOT_FOUND));

        Ride ride = rideRepository.findById(request.getRideId())
                        .orElseThrow(() -> new BadRequestException(RIDE_FIELD, RIDE_NOT_FOUND));

        if(reservation.getNumberOfSeatsReserved() > ride.getAvailableSeats()){
            throw new BadRequestException(RESERVATION_FIELD, NUMBER_OF_SEATS);
        }

        ride.setAvailableSeats(ride.getAvailableSeats() - reservation.getNumberOfSeatsReserved());
        reservation.setUser(user);
        reservation.setRide(ride);

        rideRepository.save(ride);
        reservationRepository.save(reservation);
        return new ApiResponse("Reservation created",true);
    }

    public List<Reservation> getReservations(String email){
        return reservationRepository.findAllByUserEmail(email);
    }

    public ApiResponse cancelReservation(Long id, String email){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(RESERVATION_FIELD, RESERVATION_NOT_FOUND));

        if(!reservation.getUser().getEmail().equals(email)){
            throw new BadRequestException(RESERVATION_FIELD, NOT_ALLOWED_TO_CANCEL);
        }

        if(reservation.getRide().getDate().isBefore(LocalDateTime.now())){
            throw new BadRequestException(RESERVATION_FIELD, NOT_ALLOWED_TO_CANCEL);
        }

        Ride ride = reservation.getRide();
        ride.setAvailableSeats(ride.getAvailableSeats() + reservation.getNumberOfSeatsReserved());

        rideRepository.save(ride);
        reservationRepository.delete(reservation);
        return new ApiResponse("Reservation cancelled",true);
    }
}
