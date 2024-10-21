package com.rideconnect.server.controller;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateCarRequest;
import com.rideconnect.server.model.Car;
import com.rideconnect.server.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCar(@RequestBody CreateCarRequest request){
        ApiResponse response = carService.createCar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Car car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCar(@PathVariable Long id){
        ApiResponse response = carService.deleteCarById(id);
        return ResponseEntity.ok(response);
    }
}
