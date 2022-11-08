package by.salov.tms.courseproject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class IndexController {

    @Value("${url.index}")
    private String indexUrl;
    @GetMapping()
    public String getIndexTemplate() { return indexUrl; }
}
