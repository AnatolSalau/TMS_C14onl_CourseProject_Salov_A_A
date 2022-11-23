package by.salov.tms.courseproject.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BeansSecurityCongiguration {

    /**Get Data sourse bean*/
    @Autowired
    private DataSource dataSource;

    /**Create PersistentTokenRepository - repository for saving validation token
     * in DB */
    @Bean(name = "persistentTokenRepository")
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        DataSource dataSource = this.dataSource;
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
    /**Create BCryptPasswordEncoder for encoding passwords */
    @Bean(name = "bCryptPasswordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
