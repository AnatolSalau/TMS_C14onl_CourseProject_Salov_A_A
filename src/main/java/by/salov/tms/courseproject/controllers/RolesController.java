package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

    @GetMapping("${url.user}" + "/{login}")
    public ModelAndView getUserTemplate(@PathVariable String login) {
        ModelAndView modelAndView = new ModelAndView(userHtml);
        User userByLogin = userDBService.findUserByLogin(login);
        modelAndView.addObject("user", userByLogin);
        return modelAndView;
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
