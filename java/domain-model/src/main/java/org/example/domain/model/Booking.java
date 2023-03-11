package org.example.domain.model;

public class Booking {

    private String id;
    private User user;
    private Flight flight;
    private int passengers;

    public Booking(String id, User user, Flight flight, int passengers) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.passengers = passengers;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getPassengers() {
        return passengers;
    }
}
