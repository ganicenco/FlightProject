package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Booking;
import ro.itschool.entity.User;
import ro.itschool.repository.BookingRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.BookingService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Override
    public Booking newBooking(Booking booking) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        booking.setUser(optionalLoggedInUser.get());
        booking.setAirplane(booking.getAirplane());
        booking.setTravelDetails(booking.getTravelDetails());
        booking.setUserDetails(booking.getUserDetails());
        return bookingRepository.save(booking);

    }

    @Override
    public Optional<Booking> findAllBookings() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        return bookingRepository.findById(optionalLoggedInUser.get().getId());
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public void modifyBooking(Booking booking) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        booking.setUser(optionalLoggedInUser.get());
        bookingRepository.save(booking);

    }
}
