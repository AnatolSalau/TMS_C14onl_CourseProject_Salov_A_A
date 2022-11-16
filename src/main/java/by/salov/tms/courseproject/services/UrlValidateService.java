package by.salov.tms.courseproject.services;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlValidateService {

    @Autowired
    private UserDBService userDBService;

    public boolean validatePathVariableByLogin(String pathVariable, User user) {
        if (user.getLogin().equals(pathVariable)) return true;
        return false;
    }
}
