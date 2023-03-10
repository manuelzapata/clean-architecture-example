package org.example.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String id;
    private Route route;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableSeats;
    private BigDecimal price;
    private boolean isAvailable;
    private List<Booking> bookings;

    public Flight(String id) {
        this.id = id;
        this.isAvailable = true;
    }

    public Flight(String id, Route route, LocalDateTime departureTime,
                  LocalDateTime arrivalTime, int availableSeats, BigDecimal price) {
        this(id);
        this.route = route;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.price = price;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        availableSeats -= booking.getPassengers();
    }

    public boolean isAvailable(int passengers) {
        return availableSeats >= passengers;
    }

    public String getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString() {
        return id;
    }
}

