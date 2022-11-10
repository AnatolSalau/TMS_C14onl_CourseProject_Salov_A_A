package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private StringUtils stringUtils;

    @GetMapping("${url.test_crud}")
    public ModelAndView getTestCrudTemplate() {
        ModelAndView modelAndView = new ModelAndView(testCrudHtml);

        Map<Long, User> allUsersMap = userDBService.findAllUsersMap();;
        List<User> all = userJpaRepository.findAll();

        modelAndView.addObject("allUsersList", all);
        modelAndView.addObject("allUserMap", allUsersMap);

        return modelAndView;
    }
    @PostMapping ("${url.test_crud}")
    public ModelAndView postTestCrudTemplate(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(testCrudHtml);

        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        modelAndView.addObject("parameterMap", parameterMap);

        List<String> trimmedKeys = stringUtils.ifKeyStartWithTrimIt(parameterMap, "deleteUserById-");
        trimmedKeys.forEach(key -> {
            userDBService.deleteUserById(Long.getLong(key));
        });


        Map<Long, User> allUsersMap = userDBService.findAllUsersMap();
        modelAndView.addObject("allUserMap", allUsersMap);

        return modelAndView;
    }
}
