package by.salov.tms.courseproject.handlers;


import by.salov.tms.courseproject.configurations.UrlHtmlNamesCongiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final String ACCESS_DENIED_ATTRIBUTE = "access_denied";
    private final String ACCESS_DENIED_URI_ATTRIBUTE = "access_denied_uri";

    @Autowired
    UrlHtmlNamesCongiguration.Urls urls;
    @Autowired
    UrlHtmlNamesCongiguration.HtmlNames htmlNames;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String requestURI = request.getRequestURI();

        request.getSession().setAttribute(ACCESS_DENIED_ATTRIBUTE, "true");
        request.getSession().setAttribute(ACCESS_DENIED_URI_ATTRIBUTE, requestURI);
        response.sendRedirect(urls.ACCESS_DENIED);
    }
}
