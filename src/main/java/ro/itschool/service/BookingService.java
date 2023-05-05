package ro.itschool.service;

import ro.itschool.entity.Booking;

import java.util.Optional;

public interface BookingService {
    Booking newBooking(Booking booking);

    Optional<Booking> findAll();
}
