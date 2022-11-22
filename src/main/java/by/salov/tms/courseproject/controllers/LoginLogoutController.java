package by.salov.tms.courseproject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Controller for output Login and Logout template of Spring Security*/
@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class LoginLogoutController {

    @Value("${html.login}")
    private String loginHtml;

    @Value("${html.logout}")
    private String logoutHtml;

    @GetMapping("/${url.login}")
    public String getLoginTemplate() {
        return loginHtml;
    }

    @GetMapping("/${url.logout}")
    public String getLogoutTemplate() {
        return logoutHtml;
    }
}
