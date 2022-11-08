package by.salov.tms.courseproject.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public  class UrlHtmlNamesCongiguration {

    @Bean
    public Urls urls() {
        return new Urls();
    }
    @Bean
    public HtmlNames HtmlNames() {
        return new HtmlNames();
    }

     public  class Urls {
        public final String ACCESS_DENIED = "accessdenied";
        public final String INDEX = "/";
        public final String USER = "user";
        public final String DOCTOR = "doctor";
        public final String ADMIN = "admin";
        public final String LOGIN = "login";
    }

     public  class HtmlNames {
        public final String ACCESS_DENIED = "access_denied.html";
        public final String INDEX = "/";
        public final String USER = "user.html";
        public final String DOCTOR = "doctor.html";
        public final String ADMIN = "admin.html";
        public final String LOGIN = "login.html";
    }
}

