package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByLogin (String login);
}
