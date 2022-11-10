package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByLogin (String login);

    List<User> findAll();

    User deleteUserById(Long id);
}
