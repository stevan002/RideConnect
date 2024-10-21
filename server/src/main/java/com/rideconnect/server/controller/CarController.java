package com.rideconnect.server.controller;

import com.rideconnect.server.dto.CreateCarRequest;
import com.rideconnect.server.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CreateCarRequest request){
        return ResponseEntity.ok().body(carService.createCar(request));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok().body(carService.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id){
        return ResponseEntity.ok().body(carService.getCarById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        return ResponseEntity.ok().body(carService.deleteCarById(id));
    }
}
