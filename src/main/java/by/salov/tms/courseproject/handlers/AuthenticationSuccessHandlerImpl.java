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
import java.util.Map;

/**
 * Implementation AuthenticationSuccessHandler from SpringSecurity
 * to redirect on login based on role */
@Component
@PropertySource("classpath:url_html.properties")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Value("${url.user}")
    private String userUrl;
    @Value("${url.admin}")
    private String adminUrl;
    @Value("${url.doctor}")
    private String doctorUrl;
    @Value("${url.patient}")
    private String patientUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException,
            ServletException {
        String userLogin = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        /*Get list of User roles*/
        List<String> rolesList = authorities.stream()
                .map(Object::toString)
                .toList();

        for (String role : rolesList) {
                if (Role.ROLE_USER.toString().equals(role)) {
                    response.sendRedirect(userUrl + "/" + userLogin);
                    break;
                }
                else if (Role.ROLE_DOCTOR.toString().equals(role)) {
                    response.sendRedirect(doctorUrl + "/" + userLogin);
                    break;
                }
                else if (Role.ROLE_PATIENT.toString().equals(role)) {
                    response.sendRedirect(patientUrl + "/" + userLogin);
                    break;
                }
                else  if (Role.ROLE_ADMIN.toString().equals(role)) {
                    response.sendRedirect(adminUrl + "/" + userLogin);
                    break;
                }
        }
    }
}
