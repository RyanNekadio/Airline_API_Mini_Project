package com.example.airline_api.controllers;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    // Display details of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        List<Passenger> passengers = passengerService.getPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    // Display specific passenger details
    @GetMapping(value = "/{id}")
    public ResponseEntity<Passenger> getPassengerById(){
        return null;
    }

    // Add a new passenger
    @PostMapping
    public ResponseEntity<Passenger> addNewPassenger(@RequestBody Passenger passenger){
        Passenger newPassenger = passengerService.addPassenger(passenger);
        return new ResponseEntity<>(newPassenger, HttpStatus.CREATED);
    }

}