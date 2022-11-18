package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.repositories.UserRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserRoleDBService {

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    public UserRole addRoleToUser(Role role, User user) throws UserException {
        User userByLogin = findUserByLogin(user.getLogin());
        UserRole newUserRole = new UserRole(role,userByLogin);
        userJpaRepository.save(userByLogin);
        UserRole savedUserRole = userRoleJpaRepository.save(newUserRole);
        return savedUserRole;
    }

    public UserRole deleteRoleFromUserByRoleName(Role role, User user) throws UserException {
        String roleName = role.toString();
        User userByLogin = findUserByLogin(user.getLogin());
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

        UserRole userRoleById = userRoleJpaRepository.findUserRoleById(idDeletedRole);
        if (userRoleById == null) {
            throw new UserException("UserRole with id" + idDeletedRole + "not found");
        }
        return userRoleById;
    }

    private User findUserByLogin(String login) throws UserException {
        User userFromDB = userJpaRepository.findUserByLogin(login).orElseThrow(
                () -> new UserException("User with login" + login + "not found")
        );
        return userFromDB;
    }
}
