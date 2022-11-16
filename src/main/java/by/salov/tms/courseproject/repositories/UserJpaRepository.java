package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserJpaRepository extends JpaRepository<User,Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByLogin (String login);

    List<User> findAll();

    void deleteUserById(Long id);

    void deleteUserByLogin(String login);
}
