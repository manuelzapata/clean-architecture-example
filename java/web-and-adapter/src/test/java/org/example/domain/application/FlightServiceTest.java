package org.example.domain.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.example.domain.application.interfaces.persistence.BookingRepository;
import org.example.domain.application.interfaces.persistence.FlightRepository;
import org.example.domain.model.Flight;
import org.example.domain.model.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private BookingRepository bookingRepository;

    @Test
    void testSearch() {
        // Arrange
        Route route = new Route("New York", "Los Angeles");
        LocalDate departureDate = LocalDate.of(2023, 4, 1);
        int passengers = 3;
        
        // Mock the behavior of the flight repository
        Flight flight1 = new Flight("UA101", new Route("New York", "Los Angeles"), LocalDateTime.of(2023, 4, 1, 7, 0), LocalDateTime.of(2023, 4, 1, 13, 30), 100, new BigDecimal(299.990));
        Flight flight2 = new Flight("UA102", new Route("New York", "Los Angeles"), LocalDateTime.of(2023, 4, 1, 11, 0), LocalDateTime.of(2023, 4, 1, 17, 30), 50, new BigDecimal(329.99));
        when(flightRepository.search(route, departureDate)).thenReturn(Arrays.asList(flight1, flight2));
        
        // Create the flight service
        FlightService flightService = new FlightService(flightRepository, bookingRepository);
        
        // Act
        List<Flight> flights = flightService.search(route, departureDate, passengers);
        
        // Assert
        assertEquals(2, flights.size());
        assertEquals(flight1, flights.get(0));
        
        // Verify that the flight repository was called with the correct arguments
        verify(flightRepository, times(1)).search(ArgumentMatchers.eq(route), ArgumentMatchers.eq(departureDate));
    }
}
