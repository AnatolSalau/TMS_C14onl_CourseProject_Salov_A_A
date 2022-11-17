package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void deleteUserByIdTest() {
        userJpaRepository.deleteUserById(1L);
    }

    @Test
    void saveUserTest() {
        User newUser = new User(
                "Anatoly", "Salov", "1111", "anatoly1", Role.ROLE_USER
        );
        userJpaRepository.save(newUser);
    }

    @Test
    void findUserByLoginTest() {
        User vika = userJpaRepository.findUserByLogin("vika").orElse(null);
        System.out.println(vika);
    }
}