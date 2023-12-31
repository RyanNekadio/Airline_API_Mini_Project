package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.models.Passenger;
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
    public ResponseEntity<List<Flight>> getAllFlightsAndFilters(
            @RequestParam(required = false, name = "destination") String destination
    ){
//        Filter flights by destination
        if(destination != null){
            return new ResponseEntity<>(
                    flightService.filterFlightsByDestination(destination),
                    HttpStatus.OK);
        }
//        Get all flights
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id){
        Flight flight = flightService.getFlightById(id).get();
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
    public ResponseEntity<Flight> addPassengerToFlight(@RequestParam Long passengerId,
                                                       @PathVariable Long id){

        Flight bookPassengerToFlight = flightService.bookPassenger(passengerId, id);
        return new ResponseEntity<>(bookPassengerToFlight, HttpStatus.OK);
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id){
        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
