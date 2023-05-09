package ro.itschool.service;

import ro.itschool.entity.Booking;

import java.util.Optional;

public interface BookingService {
    void newBooking(Booking booking);

    Optional<Booking> findAllBookings();

    void cancelBooking(Long bookingID);

    void modifyBooking(Booking booking);
}
