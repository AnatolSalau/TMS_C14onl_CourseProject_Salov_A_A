package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDBService  {
    @Autowired
    private UserJpaRepository userJpaRepository;

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

    public User saveUser(User user) {
        User savedUser = userJpaRepository.save(user);
        return savedUser;
    }

    public User findUserByLogin(String login) {
        String loginTrimmedLowerCase = null;
        loginTrimmedLowerCase = login.toLowerCase().trim();
        User userByLogin = userJpaRepository.findUserByLogin(loginTrimmedLowerCase).orElse(null);
            return userByLogin;
    }
}
