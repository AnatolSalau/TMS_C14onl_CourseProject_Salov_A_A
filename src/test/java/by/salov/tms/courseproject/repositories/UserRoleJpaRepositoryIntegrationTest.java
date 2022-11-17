package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.dao.UserRoleDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Long id = 3L;
        User user = userJpaRepository.findUserByLogin("anatoly1").orElse(null);
        System.out.println(user);
        Set<UserRole> userRoles = user.getUserRoles();
        System.out.println(userRoles);
        userRoles.forEach(
                userRole -> {
                    if (userRole.getId() == 3L) {
                        userRoles.remove(userRole);
                    }
                }
        );
        System.out.println(userRoles);
        userJpaRepository.save(user);
        User user2 = userJpaRepository.findUserByLogin("anatoly1").orElse(null);
        System.out.println(user2);
        userRoleJpaRepository.deleteUserRoleById(id);
    }

    @Test
    void saveUserRoleTest() {
        User user = userJpaRepository.findUserByLogin("anatoly1").orElse(null);
        UserRole userRole = new UserRole(Role.ROLE_DOCTOR,user);
        userRoleDBService.saveUserRole(userRole);
    }
}