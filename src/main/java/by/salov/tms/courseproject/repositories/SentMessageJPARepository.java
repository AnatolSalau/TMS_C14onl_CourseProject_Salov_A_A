package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.SentMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface SentMessageJPARepository extends JpaRepository<SentMessage,Long> {

    Optional<SentMessage> findSentMessageById(Long id);

    void deleteMessageById(Long id);

    List<SentMessage> findAll();
}
