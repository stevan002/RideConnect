package com.rideconnect.server.mapper;

import com.rideconnect.server.dto.CreateCarRequest;
import com.rideconnect.server.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {
    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);
    Car createCar(CreateCarRequest request);
}
