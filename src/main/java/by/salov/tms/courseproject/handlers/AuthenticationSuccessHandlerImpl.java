package by.salov.tms.courseproject.handlers;

import by.salov.tms.courseproject.entities.roles.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
@PropertySource("classpath:url_html.properties")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Value("${html.user}")
    private String userHtml;
    @Value("${html.admin}")
    private String adminHtml;
    @Value("${html.doctor}")
    private String doctorHtml;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException,
            ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        /*Get list of User roles*/
        List<String> rolesList = authorities.stream()
                .map(Object::toString)
                .toList();
        rolesList.stream().forEach( role -> {
                try {
                    if (Role.ROLE_USER.toString().equals(role)) {
                        response.sendRedirect(userHtml);
                    }
                    else if (Role.ROLE_DOCTOR.toString().equals(role)) {
                        response.sendRedirect(doctorHtml);
                    }
                    else  if (Role.ROLE_ADMIN.toString().equals(role)) {
                        response.sendRedirect(adminHtml);
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        );
    }
}
