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
    private final String ACCESS_DENIED_URL_ATTRIBUTE_NAME = "access_denied_url";

    @Value("${url.access_denied}")
    private String accessDeniedUrl;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        StringBuffer requestURL = request.getRequestURL();
        String requestURLString = requestURL.toString();
        StringBuffer delete = requestURL.delete(22, requestURL.length());
        request.getSession().setAttribute(ACCESS_DENIED_ATTRIBUTE_NAME, "true");
        request.getSession().setAttribute(ACCESS_DENIED_URL_ATTRIBUTE_NAME, requestURLString);
        response.sendRedirect(delete + accessDeniedUrl);
    }
}
