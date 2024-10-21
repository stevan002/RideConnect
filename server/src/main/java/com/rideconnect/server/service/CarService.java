package com.rideconnect.server.service;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateCarRequest;
import com.rideconnect.server.model.Car;
import com.rideconnect.server.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rideconnect.server.mapper.CarMapper.CAR_MAPPER;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public ApiResponse createCar(CreateCarRequest request){
        Car car = CAR_MAPPER.createCar(request);
        carRepository.save(car);
        return new ApiResponse("Car created successfully", true);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarById(Long id){
        return carRepository.findById(id).orElse(null);
    }

    public ApiResponse deleteCarById(Long id){
        carRepository.deleteById(id);
        return new ApiResponse("Car deleted successfully", true);
    }
}
