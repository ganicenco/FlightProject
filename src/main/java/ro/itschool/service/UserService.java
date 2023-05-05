package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.User;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();
}
