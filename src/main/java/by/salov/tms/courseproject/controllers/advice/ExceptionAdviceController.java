package by.salov.tms.courseproject.controllers.advice;

import by.salov.tms.courseproject.exceptions.UserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
@PropertySource("classpath:url_html.properties")
public class ExceptionAdviceController {

    private final String NAME_EXCEPTION_ATTRIBUTE = "exceptionMessage";

    @Value("${html.exception}")
    private String exceptionHtml;

    @ExceptionHandler({UserException.class,IOException.class, UsernameNotFoundException.class})
    public ModelAndView handleUserException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(exceptionHtml);
        String message = ex.getMessage();
        modelAndView.addObject(NAME_EXCEPTION_ATTRIBUTE,message);
        return modelAndView;
    }
}
