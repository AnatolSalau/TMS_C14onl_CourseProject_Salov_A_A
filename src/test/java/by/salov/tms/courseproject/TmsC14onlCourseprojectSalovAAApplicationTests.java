package by.salov.tms.courseproject;

import by.salov.tms.courseproject.dao.DoctorDBService;
import by.salov.tms.courseproject.dao.PatientDBService;
import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.dao.UserRoleDBService;
import by.salov.tms.courseproject.entities.ReceivedMessage;
import by.salov.tms.courseproject.entities.SentMessage;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.exceptions.UserException;
import by.salov.tms.courseproject.repositories.ReceivedMessageJPARepository;
import by.salov.tms.courseproject.repositories.SentMessageJPARepository;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.repositories.UserRoleJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class TmsC14onlCourseprojectSalovAAApplicationTests {


    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private SentMessageJPARepository sentMessageJPARepository;

    @Autowired
    private ReceivedMessageJPARepository receivedMessageJPARepository;

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Autowired
    private UserRoleDBService userRoleDBService;

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private DoctorDBService doctorDBService;

    @Autowired
    private PatientDBService patientDBService;

    private User userAnatoly = new User(
            "FirstAnatoly",
            "SecondAnatoly",
            "1111",
            "anatoly",
            Role.ROLE_USER);

    private UserRole userDoctor = new UserRole(Role.ROLE_DOCTOR,userAnatoly);

    private SentMessage messageOne = new SentMessage();
    @Test
    public void saveUserTest() {
        userJpaRepository.save(userAnatoly);
    }
    @Test
    public void deleteUserByLoginTest() {
        String login = this.userAnatoly.getLogin();
        userJpaRepository.deleteUserByLogin(login);
    }

    @Test
    public void addRoleToUserTest() {
        User userByLogin = userJpaRepository.findUserByLogin(userAnatoly.getLogin()).orElse(null);
        UserRole userRoleDoctor = new UserRole(Role.ROLE_DOCTOR,userByLogin);
        userJpaRepository.save(userByLogin);
        userRoleJpaRepository.save(userRoleDoctor);
    }

    @Test
    public void deleteRoleByRoleNameTest() {
        String roleName = this.userDoctor.getRoleName();
        User userByLogin = userJpaRepository.findUserByLogin(userAnatoly.getLogin()).orElse(null);
        Set<UserRole> userRoles = userByLogin.getUserRoles();
        Long idDeletedRole = null;
        UserRole deletedUserRoleFromUser = null;
        for (UserRole userRole : userRoles) {
            if (userRole.getRoleName().equals(roleName)) {
                idDeletedRole = userRole.getId();
                deletedUserRoleFromUser = userRole;
            }
        }
        userRoles.remove(deletedUserRoleFromUser);
        userJpaRepository.save(userByLogin);
        userRoleJpaRepository.deleteUserRoleById(idDeletedRole);
    }

    @Test
    public void addRoleToUser() throws UserException {
        User anatoly = userJpaRepository.findUserByLogin("anatoly").orElse(null);
        Role doctorRole = Role.ROLE_DOCTOR;
        userRoleDBService.addRoleToUser(doctorRole, userAnatoly.getLogin());

    }

    @Test
    public void changeUserRoles() throws UserException {
        User user = userDBService.saveUser(userAnatoly);
        User userBefore= userDBService.findUserByLogin(userAnatoly.getLogin());
        System.out.println(userBefore);
        userRoleDBService.addRoleToUser(Role.ROLE_DOCTOR, userAnatoly.getLogin());

        User userAfter= userDBService.findUserByLogin(userAnatoly.getLogin());
        System.out.println(userAfter);
    }

    @Test
    void deleteDoctorFromUserTest() {
        doctorDBService.deleteDoctorFromUser("vika");
    }

    @Test
    void deleteRoleFromUser() throws UserException {
        userRoleDBService.deleteRoleFromUserByRole(Role.ROLE_DOCTOR, "vika");
    }
    /*----------------------------------------------------------------------------------------------*/
    @Test
    void addDoctorToPatientTest() {
        patientDBService.addDoctorToPatient("vika","anatoly");
    }

    @Test
    void deleteDoctorFromPatientTest() {
        patientDBService.deleteDoctorFromPatient("vika","anatoly");
    }

    @Test
    void addPatientToDoctorTest() {
        doctorDBService.addPatientToDoctor("anatoly", "vika");
    }

    @Test
    void deletePatientFromDoctor() {
        doctorDBService.deletePatientFromDoctor("anatoly", "vika");
    }

    @Test
    void saveMessagesTest() {
        User reader = userJpaRepository.findUserByLogin("anatoly").orElse(null);
        SentMessage sentMessage = new SentMessage("FirstMessage", reader);

        User writer = userJpaRepository.findUserByLogin("vika").orElse(null);
        ReceivedMessage receivedMessage = new ReceivedMessage("FirstMessage", writer);

        sentMessageJPARepository.save(sentMessage);
        receivedMessageJPARepository.save(receivedMessage);
    }
}
