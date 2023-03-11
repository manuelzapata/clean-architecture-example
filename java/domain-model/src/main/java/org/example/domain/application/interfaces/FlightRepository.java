package org.example.domain.application.interfaces;

import org.example.domain.model.Flight;
import org.example.domain.model.Route;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository {

    List<Flight> search(Route route, LocalDate departureDate);

    void save(Flight flight);

}
