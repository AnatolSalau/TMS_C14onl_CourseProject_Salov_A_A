package by.salov.tms.courseproject.configurations;

import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.handlers.AccessDeniedHandlerImpl;
import by.salov.tms.courseproject.handlers.AuthenticationSuccessHandlerImpl;
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
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity()
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
/** load values from url_html.properties file*/
@PropertySource("classpath:url_html.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${url.user}")
    private String userUrl;
    @Value("${url.admin}")
    private String adminUrl;
    @Value("${url.doctor}")
    private String doctorUrl;
    @Value("${url.patient}")
    private String patientUrl;
    @Value("${url.login}")
    private String loginUrl;
    @Value("${url.perform_login}")
    private String performLoginUrl;
    @Value("${url.logout}")
    private String logoutUrl;
    @Value("${url.test_controller}")
    private String testControllerUrl;
    @Value("${url.create_account}")
    private String createAccountUrl;

    /** Load Handler for handling "access denied" in Spring Security*/
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    /** Load service for generating SpringSecurity UserDetails class*/
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    /** Load Handler for handling success enter in Spring Security*/
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

    /** Load bean for saving token in DB*/
    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    /** load in authentication our beans for UserDetails and PasswordEncoder*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /** setting spring security*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/" + adminUrl + "/**","/" + testControllerUrl + "/**")//first indicate the narrowest urls
                .hasRole(Role.ROLE_ADMIN.getRoleName())
                .antMatchers("/" + doctorUrl + "/**")
                .hasAnyRole(Role.ROLE_DOCTOR.getRoleName(), Role.ROLE_ADMIN.getRoleName())
                .antMatchers("/" + patientUrl + "/**")
                .hasAnyRole(Role.ROLE_PATIENT.getRoleName(), Role.ROLE_ADMIN.getRoleName())
                .antMatchers("/" + userUrl + "/**")
                .hasAnyRole(Role.ROLE_USER.getRoleName(),
                        Role.ROLE_PATIENT.getRoleName(), Role.ROLE_DOCTOR.getRoleName(),
                        Role.ROLE_ADMIN.getRoleName())//first indicate the common urls
                .antMatchers("/**", createAccountUrl)
                .permitAll()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/" + loginUrl)
                .loginProcessingUrl("/" + performLoginUrl)
                .usernameParameter("username")//parameters name from html
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .successHandler(authenticationSuccessHandlerImpl)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandlerImpl)
                .and()
                .rememberMe() // configure remember me,
                .tokenRepository(persistentTokenRepository) // persistent token for remember me
                .rememberMeParameter("remember-me")// remember me field name in login form
                .tokenValiditySeconds(86400)// keep valid for one day
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/" + logoutUrl)
                .logoutSuccessUrl("/" + loginUrl);
    }
}
