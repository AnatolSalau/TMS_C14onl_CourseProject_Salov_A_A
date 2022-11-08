package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.configurations.UrlHtmlNamesCongiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @Autowired
    UrlHtmlNamesCongiguration.Urls urls;

    @GetMapping()
    public String getIndexTemplate() { return urls.INDEX; }
}
