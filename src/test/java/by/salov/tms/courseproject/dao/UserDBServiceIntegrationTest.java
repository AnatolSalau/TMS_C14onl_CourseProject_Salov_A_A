package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDBServiceIntegrationTest {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    void saveUserTest() {
    }
}