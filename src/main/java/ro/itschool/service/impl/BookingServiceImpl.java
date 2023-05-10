package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Booking;
import ro.itschool.entity.User;
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
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
        //   loggedInUser.getBookingList()

        //.removeIf(booking -> booking.getId() == bookingId);


//
//                .stream()
//                .filter(booking -> Objects.equals(booking.getId(), bookingId))
//                .toList()
//                .forEach(booking -> bookingRepository.deleteById(bookingId));


        bookingRepository.findById(bookingId)
                .ifPresentOrElse(booking -> bookingRepository.deleteById(bookingId), () -> System.out.println("Booking not found"));
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
