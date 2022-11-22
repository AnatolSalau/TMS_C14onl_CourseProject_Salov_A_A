package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.ReceivedMessage;
import by.salov.tms.courseproject.entities.SentMessage;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.ReceivedMessageJPARepository;
import by.salov.tms.courseproject.repositories.SentMessageJPARepository;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/** Service for CRUD operations on Message entity*/
@Service
public class MessagesDBService {
    @Autowired
    private SentMessageJPARepository sentMessageJPARepository;

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ReceivedMessageJPARepository receivedMessageJPARepository;


    public void saveMessages(String readerLogin, String writerLogin, String text) {
        User reader = userJpaRepository.findUserByLogin(readerLogin).orElse(null);
        SentMessage sentMessage = new SentMessage(text, reader);

        User writer = userJpaRepository.findUserByLogin(writerLogin).orElse(null);
        ReceivedMessage receivedMessage = new ReceivedMessage(text, writer);

        sentMessageJPARepository.save(sentMessage);
        receivedMessageJPARepository.save(receivedMessage);
    }

    public Map<SentMessage, User>getAllSentMessages() {
        List<SentMessage> all = sentMessageJPARepository.findAll();
        Map<SentMessage, User> collect = all.stream().collect(Collectors.toMap(message -> message, message -> message.getAuthor()));
        return collect;
    }

    public Map<String,ReceivedMessage> getAllReceivedMessages() {
        List<ReceivedMessage> all = receivedMessageJPARepository.findAll();
        Map<String, ReceivedMessage> collect = all.stream().collect(Collectors.toMap(ReceivedMessage::getText, message -> message));
        return collect;
    }
}
