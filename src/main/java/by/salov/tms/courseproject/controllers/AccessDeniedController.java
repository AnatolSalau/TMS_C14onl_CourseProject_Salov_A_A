package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.configurations.UrlHtmlNamesCongiguration;
import by.salov.tms.courseproject.handlers.AccessDeniedHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class AccessDeniedController extends UrlsHtmlNamesController {

    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    @Autowired
    UrlHtmlNamesCongiguration.HtmlNames htmlNames;

    @Autowired
    UrlHtmlNamesCongiguration.Urls urls;


    @GetMapping(ACCESS_DENIED_URL)
    public ModelAndView getAccessDeniedTemplate(HttpServletRequest httpServletRequest) {
        String accessDenied = (String)httpServletRequest
                .getSession().getAttribute(accessDeniedHandlerImpl.getACCESS_DENIED_ATTRIBUTE());
        String accessDeniedUri = (String)httpServletRequest
                .getSession().getAttribute(accessDeniedHandlerImpl.getACCESS_DENIED_URI_ATTRIBUTE());
        if (accessDenied == null) {
            ModelAndView loginModelAndView = new ModelAndView(htmlNames.LOGIN);
            return loginModelAndView;
        }
        ModelAndView accessDeniedModelAndView = new ModelAndView(htmlNames.ACCESS_DENIED);
        accessDeniedModelAndView.addObject(accessDeniedHandlerImpl.getACCESS_DENIED_ATTRIBUTE(),accessDenied);
        accessDeniedModelAndView.addObject(accessDeniedHandlerImpl.getACCESS_DENIED_URI_ATTRIBUTE(),accessDeniedUri);
        return accessDeniedModelAndView;
    }
}
