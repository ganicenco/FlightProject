package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.controller.modelDTO.UserDTO;
import ro.itschool.entity.User;
import ro.itschool.mapper.UserMapper;
import ro.itschool.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers()
                .stream()
                .map(userMapper::fromEntity)
                .toList(), HttpStatus.OK);


    }
}
