package ro.itschool.service;

import ro.itschool.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    void newBooking(Booking booking);

    List<Booking> findAllBookings();

    void cancelBooking(Long bookingId);

    void modifyBooking(Booking booking);

    Optional<Booking> findById(Long booking);

}
