package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.repositories.UserRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserRoleDBService {

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    public UserRole addRoleToUser(Role role, String login) throws UserException {


        if (isRoleInDB(role)) {
            User userByLogin = findUserByLogin(login);
            UserRole userRoleFromDB = userRoleJpaRepository.findUserRoleByRoleName(role.toString());
            userByLogin.getUserRoles().add(userRoleFromDB);
            userJpaRepository.save(userByLogin);
            return userRoleFromDB;
        } else {
            User userByLogin = findUserByLogin(login);
            UserRole newUserRole = new UserRole(role, userByLogin);
            userRoleJpaRepository.save(newUserRole);
            userByLogin.getUserRoles().add(userRoleJpaRepository.findUserRoleByRoleName(role.toString()));
            userJpaRepository.save(userByLogin);
            return userRoleJpaRepository.findUserRoleByRoleName(role.toString());
        }
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

    private boolean isRoleInDB (Role role) {
        String roleName = role.toString();
        UserRole userFromDB = userRoleJpaRepository.findUserRoleByRoleName(roleName);
        if(userFromDB == null) {
            return false;
        }
        return true;
    }
}
