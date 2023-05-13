package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Airplane;
import ro.itschool.entity.Booking;
import ro.itschool.entity.User;
import ro.itschool.exceptions.BookingNotFoundException;
import ro.itschool.exceptions.UserNotFoundException;
import ro.itschool.repository.BookingRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.BookingService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Override
    public void newBooking(Booking booking) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        booking.setUser(optionalLoggedInUser.get());
        booking.setAirplane(booking.getAirplane());
        booking.setTravelDetails(booking.getTravelDetails());
        booking.setUserDetails(booking.getUserDetails());
        bookingRepository.save(booking);

    }

    @Override
    public List<Booking> findAllBookings() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
        return loggedInUser.getBookingList().stream()
                .toList();
    }

    @Override
    public void cancelBooking(Long bookingId) {
        var bookingToBeDeleted = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found!"));
        bookingToBeDeleted.setUser(null);
        bookingRepository.delete(bookingToBeDeleted);
    }

    @Override
    public void modifyBooking(Long id, Booking updatedBooking) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        var bookingToBeModified = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found!"));
        bookingToBeModified.setAirplane(updatedBooking.getAirplane());
        bookingToBeModified.setTravelDetails(updatedBooking.getTravelDetails());
        bookingToBeModified.setUserDetails(updatedBooking.getUserDetails());
        bookingToBeModified.setUser(loggedInUser);
        bookingRepository.save(bookingToBeModified);
    }

}
