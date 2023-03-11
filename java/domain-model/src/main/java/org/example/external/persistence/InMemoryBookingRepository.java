package org.example.external.persistence;

import org.example.domain.application.interfaces.BookingRepository;
import org.example.domain.model.Booking;

import java.sql.*;

public class InMemoryBookingRepository implements BookingRepository {

    private final Connection connection;

    public InMemoryBookingRepository() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:flightdb");
        createTableIfNotExists();
    }

    @Override
    public void save(Booking booking) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO bookings (id, user_id, flight_id, passengers) VALUES (?, ?, ?, ?)");
            statement.setString(1, booking.getId());
            statement.setString(2, booking.getUser().getId());
            statement.setString(3, booking.getFlight().getId());
            statement.setInt(4, booking.getPassengers());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS bookings (" +
                "id VARCHAR(36) PRIMARY KEY, " +
                "user_id VARCHAR(36) NOT NULL, " +
                "flight_id VARCHAR(36) NOT NULL, " +
                "passengers INT NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

}
