package ro.itschool;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import ro.itschool.model.LoggedUser;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class LoggedUserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${sql.script.create.user}")
    private String sqlCreateUser;

    @Value("${sql.script.delete.user}")
    private String sqlDeleteUser;

    @BeforeEach
    public void setupDatabase(){
        jdbc.execute(sqlCreateUser);
    }

    @Test
    public void getAllUsers() {
        Iterable<LoggedUser> iterableUser = userRepository.findAll();
        List<LoggedUser> loggedUsers = new ArrayList<>();

        for (LoggedUser loggedUser : iterableUser) {
            loggedUsers.add(loggedUser);
        }
        assertEquals(1, loggedUsers.size());
    }


    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteUser);

    }

}
