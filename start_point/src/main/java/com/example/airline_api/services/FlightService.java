package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Flight addFlightDetails(Flight flight){
        flightRepository.save(flight);
        return flight;
    }
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id){
        return flightRepository.findById(id).get();
    }
}
