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
        boolean result = true;
        if(this.origin == null || this.origin.isEmpty()){
            result = false;
        }

        if(this.destination == null || this.destination.isEmpty()){
            result = false;
        }
        return result;
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
