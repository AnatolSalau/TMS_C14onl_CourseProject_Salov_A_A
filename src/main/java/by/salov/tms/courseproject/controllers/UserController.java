package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.*;
import by.salov.tms.courseproject.entities.SentMessage;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.services.AuthoritiesUpdaterService;
import by.salov.tms.courseproject.services.UrlValidateService;
import org.apache.logging.log4j.message.Message;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PatientDBService patientDBService;
    @Autowired
    private UrlValidateService urlValidateService;

    @Autowired
    private AuthoritiesUpdaterService authoritiesUpdaterService;

    @Autowired
    private MessagesDBService messagesDBService;

    @Value("${html.user}")
    private String userHtml;

    @Value("${url.user_chat}")
    private String userChatUrl;

    @Value("${html.user_chat}")
    private String userChatHtml;

    @Value("${url.user}")
    private String userUrl;

    @Value("${url.login}")
    private String loginUrl;
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

        modelAndView.addObject("user", userByLogin);
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

    @PostMapping("${url.user}" + "/{login}" +"/deleteuser")
    public RedirectView deleteUser(
            Authentication authentication
    )  {
        String login = authentication.getName();
        doctorDBService.deleteDoctorFromUser(login);
        patientDBService.deletePatientFromUser(login);
        userDBService.deleteUserByLogn(login);
        return new RedirectView("/" + loginUrl);
    }

    @GetMapping("${url.user}" + "/{login}" + "/" + "${url.user_chat}")
    public ModelAndView getUserChatTemplate(Authentication authentication) {
        String name = authentication.getName();
        User userByLogin = userDBService.findUserByLogin(name);
        Map<Long, User> allUsersMap = userDBService.findAllUsersMap();
        ModelAndView modelAndView = new ModelAndView(userChatHtml);
        Map<SentMessage, User> textAllMessages = messagesDBService.getAllSentMessages();
        modelAndView.addObject("textAllMessages", textAllMessages);
        modelAndView.addObject("user", userByLogin);
        modelAndView.addObject("allUsersMap", allUsersMap);
        return modelAndView;
    }

    @PostMapping("${url.user}" + "/{login}" + "/" + "${url.user_chat}")
    public RedirectView postUserChatTemplate(Authentication authentication,
                                             @RequestParam(name = "text", required = false)String text,
                                             @RequestParam(name = "readerLogin", required = false)List<String> readerLogins
                                             ) {
        String name = authentication.getName();
        if (readerLogins != null) {
            for (String readerLogin : readerLogins) {
                messagesDBService.saveMessages(readerLogin,name, text);
            }
        }
        return new RedirectView("/" +userUrl + "/" + name + "/" + userChatUrl);
    }
}
