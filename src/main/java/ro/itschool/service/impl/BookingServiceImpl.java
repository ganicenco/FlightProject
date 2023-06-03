package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.exceptions.BookingNotFoundException;
import ro.itschool.exceptions.UserNotFoundException;
import ro.itschool.model.Booking;
import ro.itschool.model.LoggedUser;
import ro.itschool.repository.BookingRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.BookingService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Override
    public void newBooking(Booking booking) {
        LoggedUser principal = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<LoggedUser> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        booking.setLoggedUser(optionalLoggedInUser.get());
        booking.setAirplane(booking.getAirplane());
        booking.setTravelDetails(booking.getTravelDetails());
        booking.setUserInfo(booking.getUserInfo());
        bookingRepository.save(booking);

    }

    @Override
    public List<Booking> findAllBookings() {
        LoggedUser principal = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoggedUser loggedInLoggedUser = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("LoggedUser does not exist"));
        return loggedInLoggedUser.getBookingList().stream()
                .toList();
    }

    @Override
    public void cancelBooking(Long bookingId) {
        var bookingToBeDeleted = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found!"));
        bookingToBeDeleted.setLoggedUser(null);
        bookingRepository.delete(bookingToBeDeleted);
    }

    @Override
    public void modifyBooking(Long id, Booking updatedBooking) {
        LoggedUser principal = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoggedUser loggedInLoggedUser = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("LoggedUser does not exist"));

        var bookingToBeModified = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found!"));
        bookingToBeModified.setAirplane(updatedBooking.getAirplane());
        bookingToBeModified.setTravelDetails(updatedBooking.getTravelDetails());
        bookingToBeModified.setUserInfo(updatedBooking.getUserInfo());
        bookingToBeModified.setLoggedUser(loggedInLoggedUser);
        bookingRepository.save(bookingToBeModified);
    }

}
