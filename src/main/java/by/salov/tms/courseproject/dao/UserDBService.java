package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.repositories.UserRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDBService  {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserRoleDBService userRoleDBService;
    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    public Map<Long,User> findAllUsersMap() {
        Map<Long,User> usersMap = null;
        List<User> all = userJpaRepository.findAll();
        usersMap = all.stream().collect(Collectors.toMap(User::getId, user -> user));
        return usersMap;
    }

    public void deleteUserById(Long id) {
        System.out.println(id);
        userJpaRepository.deleteUserById(id);
    }
    public void deleteUserByLogn(String login) {
        userJpaRepository.deleteUserByLogin(login);
    }

    public User saveUser(User user) {
        UserRole userRoleFromDB = userRoleJpaRepository.findUserRoleByRoleName(Role.ROLE_USER.toString());
        if(userRoleFromDB == null) {
            User savedUser = userJpaRepository.save(user);
            return savedUser;
        } else {
            user.getUserRoles().removeAll(user.getUserRoles());
            user.getUserRoles().add(userRoleFromDB);
            User savedUser = userJpaRepository.save(user);
            return savedUser;
        }
    }

    public User findUserByLogin(String login) {
        String loginTrimmedLowerCase = null;
        loginTrimmedLowerCase = login.toLowerCase().trim();
        User userByLogin = userJpaRepository.findUserByLogin(loginTrimmedLowerCase).orElse(null);
            return userByLogin;
    }
}
