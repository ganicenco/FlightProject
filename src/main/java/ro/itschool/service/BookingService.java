package ro.itschool.service;

import ro.itschool.entity.Booking;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface BookingService {
    void newBooking(Booking booking);

    List<Booking> findAllBookings();

    void cancelBooking(Long bookingId);

    void modifyBooking(Long id, Booking updatedBooking) throws Throwable;

    Optional<Booking> findById(Long booking);

}
