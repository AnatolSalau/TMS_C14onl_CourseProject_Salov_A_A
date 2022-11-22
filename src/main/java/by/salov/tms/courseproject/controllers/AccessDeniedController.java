package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.handlers.AccessDeniedHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/** Controller for showing errors from Access denied handler in spring security*/
@Controller
@RequestMapping("/")
@PropertySource("classpath:url_html.properties")
public class AccessDeniedController {

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    @Value("${html.login}")
    private String loginHtml;

    @Value("${html.access_denied}")
    private String accessDeniedHtml;

    @GetMapping("${url.access_denied}")
    public ModelAndView getAccessDeniedTemplate(HttpServletRequest httpServletRequest) {
        /** Get attributes that we put in it, in Access denied handler*/
        String accessDenied = (String)httpServletRequest
                .getSession().getAttribute(accessDeniedHandlerImpl.getACCESS_DENIED_ATTRIBUTE_NAME());
        String accessDeniedUri = (String)httpServletRequest
                .getSession().getAttribute(accessDeniedHandlerImpl.getACCESS_DENIED_URL_ATTRIBUTE_NAME());
        if (accessDenied == null) {

            ModelAndView loginModelAndView = new ModelAndView(loginHtml);
            return loginModelAndView;
        }
        ModelAndView accessDeniedModelAndView = new ModelAndView(accessDeniedHtml);
        accessDeniedModelAndView.addObject(accessDeniedHandlerImpl.getACCESS_DENIED_ATTRIBUTE_NAME(),accessDenied);
        accessDeniedModelAndView.addObject(accessDeniedHandlerImpl.getACCESS_DENIED_URL_ATTRIBUTE_NAME(),accessDeniedUri);
        return accessDeniedModelAndView;
    }
}
