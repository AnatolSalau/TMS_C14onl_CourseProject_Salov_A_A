package by.salov.tms.courseproject;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class TmsC14onlCourseprojectSalovAAApplicationTests {


    @Autowired
    private UserJpaRepository userJpaRepository;

    private User userAnatoly = new User(
            "FirstAnatoly",
            "SecondAnatoly",
            "1111",
            "anatoly",
            Role.ROLE_USER);

    private UserRole userRoleDoctor = new UserRole(Role.ROLE_DOCTOR);

    @Test
    public void saveUserTest() {
        userJpaRepository.save(userAnatoly);
    }

    @Test
    public void addRoleToUserTest() {
        User anatoly = userJpaRepository.findUserByLogin("anatoly").orElse(null);
        anatoly.addUserRole(userRoleDoctor);
        userJpaRepository.save(anatoly);
    }

    @Test
    public void deleteRoleFromUserTest() {
        User anatoly = userJpaRepository.findUserByLogin("anatoly").orElse(null);
        Set<UserRole> userRoles = anatoly.getUserRoles();
        System.out.println(userRoles);
        anatoly.removeUserRole(userRoleDoctor);
        Set<UserRole> userRoles2 = anatoly.getUserRoles();
        System.out.println(userRoles2);
        userJpaRepository.save(anatoly);
    }

    @Test
    public void removeUserRoleMethodTest() {
        System.out.println(userAnatoly);

    }
}
