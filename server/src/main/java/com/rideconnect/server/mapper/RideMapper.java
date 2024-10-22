package com.rideconnect.server.mapper;

import com.rideconnect.server.dto.CreateRideRequest;
import com.rideconnect.server.model.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RideMapper {
    RideMapper RIDE_MAPPER = Mappers.getMapper(RideMapper.class);
    Ride createRide(CreateRideRequest request);

}
