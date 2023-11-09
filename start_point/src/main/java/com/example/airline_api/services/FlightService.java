package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PassengerService passengerService;
    public Flight addFlightDetails(FlightDTO flightDTO){
        Flight flight = new Flight(
                flightDTO.getDestination(),
                flightDTO.getCapacity(),
                flightDTO.getDepartureDate(),
                flightDTO.getDepartureTime()
        );
        for (Long passengerid : flightDTO.getPassengerIds()){
            Passenger passenger = passengerService.getPassengerById(passengerid).get();
            flight.addPassenger(passenger);
        }
        flightRepository.save(flight);
        return flight;
    }
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id){
        return flightRepository.findById(id);
    }

    @Transactional
    public Flight bookPassenger(Long passengerId, Long flightId){

        Flight flightToUpdate = flightRepository.findById(flightId).get();
        flightToUpdate.addPassenger(passengerRepository.findById(passengerId).get());
        flightRepository.save(flightToUpdate);
        return flightToUpdate;
    }

    public void cancelFlight(Long id){
        flightRepository.deleteById(id);
    }

    public List<Flight> filterFlightsByDestination(String destination){
        return flightRepository.findAllFlightsByDestination(destination);
    }
}
