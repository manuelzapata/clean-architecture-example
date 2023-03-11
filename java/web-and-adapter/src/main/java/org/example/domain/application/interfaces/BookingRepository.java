package org.example.domain.application.interfaces;

import org.example.domain.model.Booking;

public interface BookingRepository {
    void save(Booking booking);
}
