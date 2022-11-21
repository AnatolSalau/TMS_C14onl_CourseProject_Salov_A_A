package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.ReceivedMessage;
import by.salov.tms.courseproject.entities.SentMessage;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface ReceivedMessageJPARepository extends JpaRepository<ReceivedMessage,Long> {

    Optional<ReceivedMessage> findReceivedMessageById(Long id);

    void deleteMessageById(Long id);

    List<ReceivedMessage> findAll();
}
