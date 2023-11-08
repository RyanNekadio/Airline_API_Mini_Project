package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.FlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightRepository flightRepository;

    public DataLoader(){
    }

    public void run(ApplicationArguments args) throws Exception{

//        PASSENGERS
        Passenger ryan = new Passenger("Ryan", "ryanekadio12@hotmail.co.uk");
        Passenger zsolt = new Passenger("Zsolt", "zsolt123@brightnetwork.com");
        Passenger colin = new Passenger("Colin", "colin246@brightnetwork.com");

        passengerService.addPassenger(ryan);
        passengerService.addPassenger(zsolt);
        passengerService.addPassenger(colin);

//        FLIGHTS
        Flight flightNYC = new Flight(
                "New York City",
                240,
                LocalDate.of(2024,12,28),
                LocalTime.of(6, 0)
        );
        flightNYC.addPassenger(ryan);
        flightRepository.save(flightNYC);


        Flight flightVegas = new Flight(
                "Las Vegas",
                220,
                LocalDate.of(2024, 12, 8),
                LocalTime.of(14, 0)
        );
        flightVegas.addPassenger(zsolt);
        flightRepository.save(flightVegas);


        Flight flightTokyo = new Flight(
                "Tokyo",
                180,
                LocalDate.of(2024, 7, 26),
                LocalTime.of(10,35)
        );
        flightTokyo.addPassenger(colin);
        flightRepository.save(flightTokyo);
    }
}
