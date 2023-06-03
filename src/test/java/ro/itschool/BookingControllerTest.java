package ro.itschool;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ro.itschool.controller.BookingController;
import ro.itschool.model.modelDto.UserDTO;
import ro.itschool.repository.BookingRepository;
import ro.itschool.service.BookingService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class BookingControllerTest {

    private static MockHttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;

    @Mock
    BookingService bookingService;

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    private UserDTO userDTO;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingController bookingController;

    @Autowired
    static
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

//    @Value("${sql.script.create.user}")
//    private String sqlCreateUser;
//
//    @Value("${sql.script.create.booking}")
//    private String sqlCreateBooking;
    @Autowired
    private static WebApplicationContext context;

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

    @BeforeEach
    public void setupDatabase() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
//        jdbc.execute(sqlCreateUser);
//        jdbc.execute(sqlCreateBooking);
    }

    @Test
    @WithMockUser(username = "roxvet", password = "pass", roles = "USER")
    public void getUserHttpRequest() throws Exception {

        userDTO.setFirstName("Roxana");
        userDTO.setLastName("Ganicenco");
        userDTO.setEmail("rganicenco@gmail.com");
//        loggedUser.setUsername("roxvet");
        entityManager.persist(userDTO);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
