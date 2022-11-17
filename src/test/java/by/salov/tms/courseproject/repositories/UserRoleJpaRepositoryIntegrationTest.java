package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.dao.UserRoleDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRoleJpaRepositoryIntegrationTest {

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Autowired
    private UserRoleDBService userRoleDBService;

    @Autowired UserJpaRepository userJpaRepository;

    @Test
    void deleteUserRoleByIdTest() {

    }

    @Test
    void deleteUserRoleTest() {
        User user = userJpaRepository.findUserByLogin("anatoly1").orElse(null);
        System.out.println(user);
        UserRole userRoleByRole = user.getUserRoleByRole(Role.ROLE_DOCTOR);
        System.out.println(userRoleByRole);
        user.removeUserRole(Role.ROLE_DOCTOR);
        System.out.println(user);
        userJpaRepository.save(user);
    }

    @Test
    void saveUserRoleTest() {
        User user = userJpaRepository.findUserByLogin("anatoly1").orElse(null);
        user.addUserRole(new UserRole(Role.ROLE_DOCTOR));
        userJpaRepository.save(user);
    }

    @Test
    void finduserRoleByIdTest() {
        UserRole userRoleById = userRoleJpaRepository.findUserRoleById(2L);
        System.out.println(userRoleById);
    }
}