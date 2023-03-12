package org.example.external.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.example.domain.application.interfaces.FlightRepository;
import org.example.domain.model.Flight;

public class InMemoryFlightRepository implements FlightRepository {

    private Connection connection;

    public InMemoryFlightRepository() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:flightdb");
        createTableIfNotExists();
    }


    @Override
    public List<Flight> search(String origin, String destination, LocalDate departureDate) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM flights WHERE origin = ? AND destination = ? AND departure_time >= ? AND departure_time <= ?");
            statement.setString(1, origin);
            statement.setString(2, destination);
            statement.setTimestamp(3, Timestamp.valueOf(departureDate.atStartOfDay()));
            statement.setTimestamp(4, Timestamp.valueOf(departureDate.atTime(23, 59)));

            ResultSet resultSet = statement.executeQuery();

            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getString("id"));
                flight.setOrigin(resultSet.getString("origin"));
                flight.setDestination(resultSet.getString("destination"));
                flight.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
                flight.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
                flight.setAvailableSeats(resultSet.getInt("available_seats"));
                flight.setPrice(resultSet.getBigDecimal("price"));
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Flight flight) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO flights(id, origin, destination, departure_time, arrival_time, available_seats, price) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, flight.getId());
            statement.setString(2, flight.getOrigin());
            statement.setString(3, flight.getDestination());
            statement.setTimestamp(4, Timestamp.valueOf(flight.getDepartureTime()));
            statement.setTimestamp(5, Timestamp.valueOf(flight.getArrivalTime()));
            statement.setInt(6, flight.getAvailableSeats());
            statement.setBigDecimal(7, flight.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableIfNotExists() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS flights(id VARCHAR(100) PRIMARY KEY, origin VARCHAR(100), destination VARCHAR(100), departure_time TIMESTAMP, arrival_time TIMESTAMP, available_seats INT, price DECIMAL)");
    }
    
}
