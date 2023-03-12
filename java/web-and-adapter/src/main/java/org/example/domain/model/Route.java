package org.example.domain.model;

import java.util.Objects;

public class Route {

    private final String origin;
    private final String destination;

    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public boolean isValid(){
        if(this.origin == null || this.origin.isBlank()){
            return false;
        }

        if(this.destination == null || this.destination.isBlank()){
            return false;
        }

        return !this.destination.equalsIgnoreCase(this.origin);
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Route)) {
            return false;
        }

        Route other = (Route) obj;
        return origin.equals(other.origin) && destination.equals(other.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }
}
