package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment. RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class UserDBServiceTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void deleteUserById() {
        userJpaRepository.deleteUserById(1L);
    }
}