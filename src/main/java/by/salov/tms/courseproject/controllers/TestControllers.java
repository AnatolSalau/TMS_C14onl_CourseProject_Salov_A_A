package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/${url.test_controller}/")
@PropertySource("classpath:url_html.properties")
public class TestControllers {

    @Value("${html.test_crud}")
    private String testCrudHtml;

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("${url.test_crud}")
    public ModelAndView getTestCrudTemplate() {
        ModelAndView modelAndView = new ModelAndView(testCrudHtml);

/*        Map<Long, User> allUsersMap = userDBService.findAllUsersMap();
        modelAndView.addObject("all_users_map",allUsersMap);*/

        List<User> all = userJpaRepository.findAll();
        modelAndView.addObject("all", all);
        return modelAndView;
    }
}
