package ro.itschool.service;

import ro.itschool.model.Booking;

import java.util.List;

public interface BookingService {
    void newBooking(Booking booking);

    List<Booking> findAllBookings();

    void cancelBooking(Long bookingId);

    void modifyBooking(Long id, Booking updatedBooking);


}
