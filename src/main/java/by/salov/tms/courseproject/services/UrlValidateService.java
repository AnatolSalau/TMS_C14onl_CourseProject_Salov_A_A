package by.salov.tms.courseproject.services;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UrlValidateService {

    public boolean validatePathVariableByLogin(
            Authentication authentication,
            String login
    ) throws UserException {
        String name = authentication.getName();
        if (name.equals(login)) return true;
        else throw new UserException("You can't switch to someone else's account");
    }
}
