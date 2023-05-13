package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.itschool.entity.Booking;

import java.util.Optional;


public interface BookingRepository extends JpaRepository<Booking, Long> {


}
