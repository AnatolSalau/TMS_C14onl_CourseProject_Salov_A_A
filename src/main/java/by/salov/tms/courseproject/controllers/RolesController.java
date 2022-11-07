package by.salov.tms.courseproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class RolesController {

    @GetMapping(path = "user")
    public String getUserTemplate() {
        return "user.html";
    }

    @GetMapping(path = "admin")
    public String getAdminTemplate() {
        return "admin.html";
    }

    @GetMapping(path = "doctor")
    public String getDoctorTemplate() {
        return "doctor.html";
    }

}
