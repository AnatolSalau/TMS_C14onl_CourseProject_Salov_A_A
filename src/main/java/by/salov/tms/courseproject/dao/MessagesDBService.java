package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.ReceivedMessage;
import by.salov.tms.courseproject.entities.SentMessage;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.ReceivedMessageJPARepository;
import by.salov.tms.courseproject.repositories.SentMessageJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessagesDBService {
    @Autowired
    private SentMessageJPARepository sentMessageJPARepository;

    @Autowired
    private ReceivedMessageJPARepository receivedMessageJPARepository;

    public HashMap <String,List<User>> getTextAllMessages() {
        HashMap <String,List<User>> result = new HashMap<>();

        Map<String, SentMessage> allSentMessages = getAllSentMessages();
        allSentMessages.entrySet().forEach( entry -> {
            User author = entry.getValue().getAuthor();
            List<User> users = new ArrayList<>();
            users.add(entry.getValue().getAuthor());
            result.put(entry.getKey(),users);
                }
        );

        Map<String, ReceivedMessage> allReceivedMessages = getAllReceivedMessages();
        allReceivedMessages.entrySet().forEach( entry -> {
            List<User> users = result.get(entry.getKey());
                users.add(entry.getValue().getReader());
                }
        );
        return result;
    }


    public Map<String, SentMessage>getAllSentMessages() {
        List<SentMessage> all = sentMessageJPARepository.findAll();
        Map<String, SentMessage> collect = all.stream().collect(Collectors.toMap(SentMessage::getText, message -> message));
        return collect;
    }

    public Map<String,ReceivedMessage> getAllReceivedMessages() {
        List<ReceivedMessage> all = receivedMessageJPARepository.findAll();
        Map<String, ReceivedMessage> collect = all.stream().collect(Collectors.toMap(ReceivedMessage::getText, message -> message));
        return collect;
    }
}
