package org.example.domain.application.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.example.domain.model.Flight;

public interface FlightRepository {

    List<Flight> search(String origin, String destination, LocalDate departureDate);

    void save(Flight flight);

}
