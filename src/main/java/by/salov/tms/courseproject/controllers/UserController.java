package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.DoctorDBService;
import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.dao.UserRoleDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.services.AuthoritiesUpdaterService;
import by.salov.tms.courseproject.services.UrlValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class UserController {

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private UserRoleDBService userRoleDBService;
    @Autowired
    private DoctorDBService doctorDBService;

    @Autowired
    private UrlValidateService urlValidateService;

    @Autowired
    private AuthoritiesUpdaterService authoritiesUpdaterService;

    @Value("${html.user}")
    private String userHtml;

    @Value("${url.user}")
    private String userUrl;

    @GetMapping("${url.user}" + "/{login}")
    public ModelAndView getUserTemplate(
            @PathVariable String login,
            Authentication authentication
    ) throws UserException {
        urlValidateService.validatePathVariableByLogin(
                authentication,login
        );

        ModelAndView modelAndView = new ModelAndView(userHtml);
        User userByLogin = userDBService.findUserByLogin(login);

        modelAndView.addObject("user", userByLogin);
        return modelAndView;
    }

}
