package by.salov.tms.courseproject.handlers;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter

@Component
@PropertySource("classpath:url_html.properties")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final String ACCESS_DENIED_ATTRIBUTE_NAME = "access_denied";
    private final String ACCESS_DENIED_URI_ATTRIBUTE_NAME = "access_denied_uri";

    @Value("${html.access_denied}")
    private String accessDeniedHtml;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        request.getSession().setAttribute(ACCESS_DENIED_ATTRIBUTE_NAME, "true");
        request.getSession().setAttribute(ACCESS_DENIED_URI_ATTRIBUTE_NAME, requestURI);
        response.sendRedirect(accessDeniedHtml);
    }
}
