package by.salov.tms.courseproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void deleteUserByIdTest() {
        userJpaRepository.deleteUserById(1L);
    }
}