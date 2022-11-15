package by.salov.tms.courseproject.services;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidateService {
    @Autowired
    private UserDBService userDBService;

    public boolean validateUserByLogin(User user) {
        User userByLogin = userDBService.findUserByLogin(user.getLogin());
        if (userByLogin == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
