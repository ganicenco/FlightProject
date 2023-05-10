package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Booking;
import ro.itschool.entity.User;
import ro.itschool.exceptions.BookingNotFoundException;
import ro.itschool.exceptions.UserNotFoundException;
import ro.itschool.repository.BookingRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.BookingService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        //return bookingRepository.findAll();
    }

    @Override
    public void cancelBooking(Long bookingId) {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User loggedInUser = userRepository.findByUsername(principal.getUsername())
//                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
//
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        booking.ifPresent(p -> {
            p.setUser(null);
            bookingRepository.save(p); // daca se comenteaza linia 56, rezultatul e acelasi
            bookingRepository.deleteById(bookingId);
        });
    }

    @Override
    public void modifyBooking(Booking booking) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(principal.getUsername()).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        booking.setUser(loggedInUser);
        bookingRepository.save(booking);

    }

    @Override
    public Optional<Booking> findById(Long booking) {
        return bookingRepository.findById(booking);
    }
}
