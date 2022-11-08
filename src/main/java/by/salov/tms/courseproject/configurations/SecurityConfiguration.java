package by.salov.tms.courseproject.configurations;

import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.handlers.AccessDeniedHandlerImpl;
import by.salov.tms.courseproject.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity (debug = true)
@EnableGlobalMethodSecurity(
 prePostEnabled = true,
 securedEnabled = true,
 jsr250Enabled = true
 )
@PropertySource("classpath:url_html.properties")
public class SecurityConfiguration<UrlHtmlNames> extends WebSecurityConfigurerAdapter {

    @Value("${url.user}")
    private String userUrl;
    @Value("${url.admin}")
    private String adminUrl;
    @Value("${url.doctor}")
    private String doctorUrl;


    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/"+ adminUrl +"/**")
                .hasRole(Role.ROLE_ADMIN.getRoleName())
                .antMatchers("/"+ doctorUrl +"/**")
                .hasAnyRole(Role.ROLE_DOCTOR.getRoleName(), Role.ROLE_ADMIN.getRoleName())
                .antMatchers("/"+ userUrl +"/**")
                .hasAnyRole(Role.ROLE_USER.getRoleName(), Role.ROLE_DOCTOR.getRoleName(), Role.ROLE_ADMIN.getRoleName())
                .antMatchers("/**")
                .permitAll()
                .and()
                .formLogin();
    }
}
