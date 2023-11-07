package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id){
        Flight flight = flightService.getFlightById(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody FlightDTO flightDTO){
        Flight flightDetails = flightService.addFlightDetails(flightDTO);
        return new ResponseEntity<>(flightDetails, HttpStatus.CREATED);
    }

//    book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@RequestBody FlightDTO flightDTO,
                                                       @PathVariable Long id){
        Flight updateFlight = flightService.updateFlight(flightDTO, id);
        return new ResponseEntity<>(updateFlight, HttpStatus.OK);
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id){
        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
