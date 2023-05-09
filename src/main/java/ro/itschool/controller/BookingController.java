package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Booking;
import ro.itschool.service.BookingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;


    @PostMapping("/new")
    public ResponseEntity<?> newBooking(@RequestBody Booking booking) {
        bookingService.newBooking(booking);
        return ResponseEntity.ok("New booking registered");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBookings() {
        var allBookings =  bookingService.findAllBookings();
         return ResponseEntity.ok(allBookings);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking with id " + bookingId + " was successfully deleted!");
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifyBooking(@RequestBody Booking booking) {
        bookingService.modifyBooking(booking);
        return ResponseEntity.ok("Your booking was successfully modified!");
    }

}
