package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Booking;
import ro.itschool.service.BookingService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;


    @PostMapping("/new")
    public void newBooking(@RequestBody Booking booking){
        bookingService.newBooking(booking);
    }
    @GetMapping("/all")
    public Optional<Booking> getAllBookings(){
        return bookingService.findAll();
    }


}
