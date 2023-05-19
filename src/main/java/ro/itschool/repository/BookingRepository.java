package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.itschool.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {


}
