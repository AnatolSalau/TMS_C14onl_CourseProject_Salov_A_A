package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.DoctorDBService;
import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.dao.UserRoleDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.services.AuthoritiesUpdaterService;
import by.salov.tms.courseproject.services.UrlValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Base64;

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
        byte[] iconBytes = userByLogin.getIcon();
        User userWithoutIcon = userByLogin;
        userWithoutIcon.setIcon(new String("icon").getBytes());

        modelAndView.addObject("user", userWithoutIcon);
        if(iconBytes != null) {
            modelAndView.addObject("pic", Base64.getEncoder().encodeToString(iconBytes));
        }

        return modelAndView;
    }
    @PostMapping("${url.user}" + "/{login}" +"/addicon")
    public RedirectView addIcon(
            Authentication authentication,
            @RequestParam(value = "icon") MultipartFile multipartFile
    ) throws IOException {
        String login = authentication.getName();
        byte[] icon = multipartFile.getInputStream().readAllBytes();
        userDBService.addIconToUser(login,icon);

        return new RedirectView("/" + userUrl + "/" + login);
    }
}
