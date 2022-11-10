package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDBService  {
    @Autowired
    private UserJpaRepository userJpaRepository;

    public Map<Long,User> findAllUsersMap() {
        Map<Long,User> usersMap = null;
        List<User> all = userJpaRepository.findAll();
        usersMap = all.stream().collect(Collectors.toMap(User::getId, user -> user));
        return usersMap;
    }

    public User deleteUserById(Long id) {
        System.out.println(id);
        User user = userJpaRepository.deleteUserById(id);
        return user;
    }
}
