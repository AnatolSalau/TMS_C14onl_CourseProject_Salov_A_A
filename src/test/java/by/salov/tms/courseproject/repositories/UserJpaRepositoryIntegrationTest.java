package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void findUserByIdTest() {
        Optional<User> userById = userJpaRepository.findUserById(1L);
        Assertions.assertEquals(1l,userById.get().getId());
    }

    @Test
    void findUserByLoginTest() {
    }

    @Test
    void findAllTest() {
        List<User> all = userJpaRepository.findAll();
        Assertions.assertEquals(3,all.size());
    }

    @Test
    @Transactional
    void deleteUserByIdTest() {
        userJpaRepository.deleteUserById(3L);
    }
}