package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class CreateAccountController {

    @Value("${html.create_account}")
    private String createAccountHtml;

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
        if (login == null ||
                password == null ||
                firstname == null ||
                secondname == null
        ) {
            modelAndView.addObject("accountCreateSuccessful", false);
        }
        User newUser = new User(firstname,secondname,password,login,Role.ROLE_USER);
        modelAndView.addObject("accountCreateSuccessful", true);
        return modelAndView;
    }
}