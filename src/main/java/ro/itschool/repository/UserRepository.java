package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
