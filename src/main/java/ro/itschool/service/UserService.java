package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.model.LoggedUser;

import java.util.List;

@Service
public interface UserService {

    List<LoggedUser> getAllUsers();
}
