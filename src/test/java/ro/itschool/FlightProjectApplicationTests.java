package ro.itschool;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import ro.itschool.model.Booking;
import ro.itschool.model.User;
import ro.itschool.repository.BookingRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application.properties")
class FlightProjectApplicationTests {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute("insert into user (username, first_name, last_name, email, password) " +
                "values ('ericr', 'Roby', 'Boby', 'eric@gmail.com', 'pass')");
        jdbc.execute("insert into booking(departure_date, return_date, " +
                "user_id, destination, first_name, last_name, origin, passport_number, plane_number, seat_number)" +
                "values ('2023-06-29T13:34:00.000', '2023-07-29T13:34:00.000', 1, 'Barcelona', 'Roxana', 'Ganicenco', 'Milano'," +
                "'123345', '234', '12C')");
    }
    //separat merg, impreuna nu.
    @Test
    void getAllUsers() {
        Iterable<User> iterableUser = userRepository.findAll();
        List<User> users = new ArrayList<>();

        for (User user : iterableUser) {
            users.add(user);
        }
        assertEquals(1, users.size());
    }

    @Test
    void getAllBookings() {
        Iterable<Booking> bookingIterable = bookingRepository.findAll();
        List<Booking> bookings = new ArrayList<>();

        for (Booking booking : bookingIterable) {
            bookings.add(booking);
        }
        assertEquals(1, bookings.size());
    }


    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("DELETE FROM booking");
        jdbc.execute("DELETE FROM user");

    }

}
