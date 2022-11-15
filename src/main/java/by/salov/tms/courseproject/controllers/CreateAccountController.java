package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.services.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class CreateAccountController {

    @Value("${html.create_account}")
    private String createAccountHtml;

    @Autowired
    private UserValidateService userValidateService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDBService userDBService;

    @GetMapping("/${url.createaccount}")
    public ModelAndView getCreateAccountTemplate() {
        ModelAndView modelAndView = new ModelAndView(createAccountHtml);

        Role[] values = Role.values();
        List<String> roleNames = Arrays.stream(values)
                .map(Role::getRoleName)
                .toList();

        modelAndView.addObject("roleNames", roleNames);
        return modelAndView;
    }

    @PostMapping("/${url.createaccount}")
    public ModelAndView postCreateAccountTemplate(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "secondname") String secondname
    ) {
        ModelAndView modelAndView = new ModelAndView(createAccountHtml);
        User newUser = null;
        if (login == null ||
                password == null ||
                firstname == null ||
                secondname == null
        ) {
            modelAndView.addObject("accountCreateSuccessful", false);
        }
        else {
            String encodedPassword = bCryptPasswordEncoder.encode(password.trim());
            newUser = new User(firstname,secondname,encodedPassword,login.toLowerCase().trim(),Role.ROLE_USER);
            encodedPassword = null;

        }
        if (userValidateService.validateUserByLogin(newUser)) {
            userDBService.saveUser(newUser);
            modelAndView.addObject("accountCreateSuccessful", true);
        }
        else {
            modelAndView.addObject("accountCreateSuccessful", false);
            modelAndView.addObject("loginExist", true);
        }

        return modelAndView;
    }
}
