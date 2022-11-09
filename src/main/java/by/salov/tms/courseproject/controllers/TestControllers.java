package by.salov.tms.courseproject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${url.test_controller}/")
@PropertySource("classpath:url_html.properties")
public class TestControllers {

    @Value("${html.test_crud}")
    private String testCrudHtml;

    @GetMapping("${url.test_crud}")
    public String getTestCrudTemplate() {
        return testCrudHtml;
    }
}
