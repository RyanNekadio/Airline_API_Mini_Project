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
                "28/12/2024",
                "06h00"
        );
        flightNYC.addPassenger(ryan);
        flightRepository.save(flightNYC);

        Flight flightVegas = new Flight(
                "Las Vegas",
                220,
                "08/12/2023",
                "14h00"
        );
        flightVegas.addPassenger(zsolt);
        flightRepository.save(flightVegas);

        Flight flightTokyo = new Flight(
                "Tokyo",
                180,
                "26/07/2024",
                "10h00"
        );
        flightTokyo.addPassenger(colin);
        flightRepository.save(flightTokyo);
    }
}
