package com.rideconnect.server.mapper;

import com.rideconnect.server.dto.CreateReservationRequest;
import com.rideconnect.server.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper RESERVATION_MAPPER = Mappers.getMapper(ReservationMapper.class);
    Reservation createReservation(CreateReservationRequest request);
}
