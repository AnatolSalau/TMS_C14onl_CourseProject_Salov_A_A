package by.salov.tms.courseproject.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRoleJpaRepositoryIntegrationTest {

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Test
    void removeUserRoleByIdTest() {
        userRoleJpaRepository.deleteUserRoleById(3L);
    }
}