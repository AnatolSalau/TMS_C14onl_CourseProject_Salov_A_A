package by.salov.tms.courseproject.controllers;

import by.salov.tms.courseproject.dao.DoctorDBService;
import by.salov.tms.courseproject.dao.PatientDBService;
import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.dao.UserRoleDBService;
import by.salov.tms.courseproject.entities.Patient;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.services.AuthoritiesUpdaterService;
import by.salov.tms.courseproject.services.UrlValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

/**Controller for output Doctor template */
@Controller
@RequestMapping(path = "/")
@PropertySource("classpath:url_html.properties")
public class DoctorController {

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private UserRoleDBService userRoleDBService;
    @Autowired
    private DoctorDBService doctorDBService;
    @Autowired
    private PatientDBService patientDBService;
    @Autowired
    private UrlValidateService urlValidateService;
    @Autowired
    private AuthoritiesUpdaterService authoritiesUpdaterService;

    @Value("${url.user}")
    private String userUrl;
    @Value("${html.doctor}")
    private String doctorHtml;

    @Value("${url.doctor}")
    private String doctorUrl;

    @PostMapping("${url.user}" + "/adddoctor")
    RedirectView addDoctorToUser(
            Authentication authentication
    ) throws UserException {
        String login = authentication.getName();
        doctorDBService.saveDoctorByUserLogin(login);
        User userByLogin = userDBService.findUserByLogin(login);

        userRoleDBService.addRoleToUser(Role.ROLE_DOCTOR,userByLogin.getLogin());
        User userByLogin2 = userDBService.findUserByLogin(login);

        authoritiesUpdaterService.update(userByLogin2);
        return new RedirectView("/" + userUrl + "/" + login);
    }

    @PostMapping("${url.user}" + "/deletedoctor")
    public RedirectView deleteDoctorFromUser(
            Authentication authentication
    ) throws UserException {
        String login = authentication.getName();
        doctorDBService.deleteDoctorFromUser(login);
        userRoleDBService.deleteRoleFromUserByRole(Role.ROLE_DOCTOR,login);

        User userByLogin = userDBService.findUserByLogin(login);

        authoritiesUpdaterService.update(userByLogin);
        return new RedirectView("/" + userUrl + "/" + login);
    }


    @GetMapping("${url.doctor}"+ "/{login}")
    public ModelAndView getDoctorTemplate(
            @PathVariable String login,
            Authentication authentication
    ) throws UserException {
        urlValidateService.validatePathVariableByLogin(
                authentication,login
        );
        ModelAndView modelAndView = new ModelAndView(doctorHtml);
        User userByLogin = userDBService.findUserByLogin(login);

        Map<Long, Patient> allPatientsFromDoctor = doctorDBService.getAllPatientsFromDoctor(login);
        Map<Long, Patient> allPatientsMap = patientDBService.findAllPatientsMap();

        modelAndView.addObject("user", userByLogin);
        modelAndView.addObject("allPatientsMap", allPatientsMap);
        modelAndView.addObject("allPatientsFromDoctor", allPatientsFromDoctor);

        return modelAndView;
    }
    @PostMapping("${url.doctor}"+ "/{login}" + "/patients")
    public RedirectView addDeletePatients(
            Authentication authentication,
            @RequestParam(name = "addUserLogin", required = false) List<String> addUserLoginList,
            @RequestParam(name = "deleteUserLogin", required = false) List<String> deleteUserLoginList
    ) throws UserException {
        String loginDoctor = authentication.getName();
        if (addUserLoginList != null) {
            for (String loginPatient : addUserLoginList) {
                doctorDBService.addPatientToDoctor(loginPatient,loginDoctor);
            }
        }
        if (deleteUserLoginList != null) {
            for (String loginPatient : deleteUserLoginList) {
                doctorDBService.deletePatientFromDoctor(loginPatient,loginDoctor);
            }
        }

        return new RedirectView("/" + doctorUrl + "/" + loginDoctor);
    }
}
