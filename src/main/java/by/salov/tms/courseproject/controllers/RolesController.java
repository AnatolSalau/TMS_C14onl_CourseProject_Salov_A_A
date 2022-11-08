package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.configurations.UrlHtmlNamesCongiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class RolesController {

    @Autowired
    UrlHtmlNamesCongiguration.Urls urls;

    @Autowired
    UrlHtmlNamesCongiguration.HtmlNames htmlNames;

    @GetMapping(urls.USER)
    public String getUserTemplate() {
        return htmlNames.USER;
    }

    @GetMapping(urls.ADMIN)
    public String getAdminTemplate() {
        return htmlNames.ADMIN;
    }

    @GetMapping(urls.DOCTOR)
    public String getDoctorTemplate() {
        return htmlNames.DOCTOR;
    }

}
