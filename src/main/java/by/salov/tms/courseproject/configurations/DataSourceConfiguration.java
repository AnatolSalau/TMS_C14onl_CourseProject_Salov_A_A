package by.salov.tms.courseproject.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    /** Load values from application.properies file */
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassNameProp;

    @Value("${spring.datasource.url}")
    private String urlProp;

    @Value("${spring.datasource.username}")
    private String usernameProp;

    @Value("${spring.datasource.password}")
    private String passwordProp;

    /** Generating DataSource for connection to DB */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassNameProp);
        dataSourceBuilder.url(urlProp);
        dataSourceBuilder.username(usernameProp);
        dataSourceBuilder.password(passwordProp);
        return dataSourceBuilder.build();
    }
}
