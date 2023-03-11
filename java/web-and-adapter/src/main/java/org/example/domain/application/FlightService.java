package org.example.domain.application;

import org.example.domain.application.interfaces.BookingRepository;
import org.example.domain.application.interfaces.FlightRepository;
import org.example.domain.model.Booking;
import org.example.domain.model.Flight;
import org.example.domain.model.Route;
import org.example.domain.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlightService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public FlightService(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Flight> search(Route route, LocalDate departureDate, int passengers) {

        // Validate search criteria
        if (!route.isValid() || departureDate == null) {
            throw new IllegalArgumentException("Search criteria are invalid.");
        }

        // Perform search based on the given parameters
        List<Flight> flights = flightRepository.search(route, departureDate);

        // Filter out flights that are full
        flights = flights.stream()
                .filter(flight -> flight.isAvailable(passengers))
                .collect(Collectors.toList());

        return flights;
    }

    public Booking bookFlight(User user, Flight flight, int passengers) {
        if (!flight.isAvailable(passengers)) {
            throw new IllegalArgumentException("The selected flight is not available for the given number of passengers.");
        }

        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, user, flight, passengers);
        flight.addBooking(booking);

        bookingRepository.save(booking);

        return booking;
    }

    public void checkSetup() {
        System.out.println(this.bookingRepository.getClass().getSimpleName());
        System.out.println(this.flightRepository.getClass().getSimpleName());
    }
}
