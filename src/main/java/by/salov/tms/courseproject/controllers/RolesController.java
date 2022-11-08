package by.salov.tms.courseproject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class RolesController {

    @Value("${html.user}")
    private String userHtml;
    @Value("${html.admin}")
    private String adminHtml;
    @Value("${html.doctor}")
    private String doctorHtml;

    @GetMapping("${url.user}")
    public String getUserTemplate() {
        return userHtml;
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
