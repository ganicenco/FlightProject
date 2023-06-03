package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.model.LoggedUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<LoggedUser, Long> {
    Optional<LoggedUser> findByUsername(String username);
}
