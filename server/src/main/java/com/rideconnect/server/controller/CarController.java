package com.rideconnect.server.controller;

import com.rideconnect.server.dto.ApiResponse;
import com.rideconnect.server.dto.CreateCarRequest;
import com.rideconnect.server.model.Car;
import com.rideconnect.server.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ApiResponse> createCar(@RequestBody CreateCarRequest request){
        ApiResponse response = carService.createCar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Car car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ApiResponse> deleteCar(@PathVariable Long id){
        ApiResponse response = carService.deleteCarById(id);
        return ResponseEntity.ok(response);
    }
}
