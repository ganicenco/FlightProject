package ro.itschool;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import ro.itschool.model.Booking;
import ro.itschool.repository.BookingRepository;
import ro.itschool.service.impl.BookingServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class BookingServiceTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JdbcTemplate jdbc;
    @Value("${sql.script.create.booking}")
    private String sqlCreateBooking;

    @Value("${sql.script.delete.booking}")
    private String sqlDeleteBooking;

    @Value("${sql.script.create.user}")
    private String sqlCreateUser;

    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlCreateUser);
        jdbc.execute(sqlCreateBooking);

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
        jdbc.execute(sqlDeleteBooking);
        jdbc.execute(sqlDeleteUser);

    }

}
