package ro.itschool.mapper;

import org.springframework.stereotype.Component;
import ro.itschool.model.LoggedUser;
import ro.itschool.model.modelDto.UserDTO;

@Component
public class UserMapper {
    public UserDTO fromEntity(LoggedUser loggedUser){
        return new UserDTO(loggedUser.getFirstName(), loggedUser.getLastName(), loggedUser.getEmail());
    }

    public LoggedUser toEntity(UserDTO userDTO){
        return new LoggedUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
    }
}
