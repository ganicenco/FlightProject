package ro.itschool.mapper;

import org.springframework.stereotype.Component;
import ro.itschool.model.modelDto.UserDTO;
import ro.itschool.model.User;

@Component
public class UserMapper {
    public UserDTO fromEntity(User user){
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public User toEntity(UserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
    }
}
