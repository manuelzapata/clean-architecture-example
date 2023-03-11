package org.example.domain.application.interfaces;

import org.example.domain.model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository {

    List<Flight> search(String origin, String destination, LocalDate departureDate);

    void save(Flight flight);

}
