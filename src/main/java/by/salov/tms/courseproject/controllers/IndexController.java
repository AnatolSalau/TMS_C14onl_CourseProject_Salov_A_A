package by.salov.tms.courseproject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**Controller for output index template */
@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class IndexController {

    @Value("${url.index}")
    private String indexUrl;
    @GetMapping()
    public String getIndexTemplate(Authentication authentication) {
        Authentication authentication1 = authentication;
        return indexUrl;
    }
}
