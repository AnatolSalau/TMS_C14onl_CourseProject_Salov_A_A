package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class RolesController {

    @Autowired
    private UserDBService userDBService;

    @Value("${html.user}")
    private String userHtml;
    @Value("${html.admin}")
    private String adminHtml;
    @Value("${html.doctor}")
    private String doctorHtml;
    @Value("${html.patient}")
    private String patientHtml;

    @GetMapping("${url.user}" + "/{login}")
    public ModelAndView getUserTemplate(
            @PathVariable String login,
            Authentication authentication
    ) throws UserException {
        /*Check current session login*/
        String name = authentication.getName();
        if (!name.equals(login)) {
            throw new UserException("You can't switch to someone else's account");
        }

        ModelAndView modelAndView = new ModelAndView(userHtml);
        User userByLogin = userDBService.findUserByLogin(login);

        modelAndView.addObject("user", userByLogin);
        return modelAndView;
    }

    @GetMapping("${url.patient}")
    public String getPatientTemplate() {
        return patientHtml;
    }

    @GetMapping("${url.admin}")
    public String getAdminTemplate() {
        return adminHtml;
    }

    @GetMapping("${url.doctor}")
    public String getDoctorTemplate() {
        return doctorHtml;
    }

}
