package by.salov.tms.courseproject;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.repositories.UserRoleJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
class TmsC14onlCourseprojectSalovAAApplicationTests {


    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    private User userAnatoly = new User(
            "FirstAnatoly",
            "SecondAnatoly",
            "1111",
            "anatoly",
            Role.ROLE_USER);

    private UserRole userRole = new UserRole(Role.ROLE_DOCTOR,userAnatoly);

    @Test
    public void saveUserTest() {
        userJpaRepository.save(userAnatoly);
    }
    @Test
    public void deleteUserByLoginTest() {
        String login = this.userAnatoly.getLogin();
        userJpaRepository.deleteUserByLogin(login);
    }

    @Test
    public void addRoleToUserTest() {
        User userByLogin = userJpaRepository.findUserByLogin(userAnatoly.getLogin()).orElse(null);
        UserRole userRoleDoctor = new UserRole(Role.ROLE_DOCTOR,userByLogin);
        userJpaRepository.save(userByLogin);
        userRoleJpaRepository.save(userRoleDoctor);
    }

    @Test
    public void deleteRoleByRoleNameTest() {
        String roleName = this.userRole.getRoleName();
        User userByLogin = userJpaRepository.findUserByLogin(userAnatoly.getLogin()).orElse(null);
        Set<UserRole> userRoles = userByLogin.getUserRoles();
        Long idDeletedRole = null;
        UserRole deletedUserRoleFromUser = null;
        for (UserRole userRole : userRoles) {
            if (userRole.getRoleName().equals(roleName)) {
                idDeletedRole = userRole.getId();
                deletedUserRoleFromUser = userRole;
            }
        }
        userRoles.remove(deletedUserRoleFromUser);
        userJpaRepository.save(userByLogin);
        userRoleJpaRepository.deleteUserRoleById(idDeletedRole);
    }
}
