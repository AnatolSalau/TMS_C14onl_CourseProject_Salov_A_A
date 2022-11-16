package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.dao.DoctorDBService;
import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.Patient;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorJpaRepositoryIntegrationTest {

    @Autowired
    DoctorJpaRepository doctorJpaRepository;

    @Autowired
    DoctorDBService doctorDBService;

    @Autowired
    UserDBService userDBService;

    @Test
    void deleteDoctorByIdTest() {
        doctorJpaRepository.deleteDoctorById(1L);
/*        Doctor doctorById = doctorJpaRepository.findDoctorById(1L);
        User user = doctorById.getUser();
        Patient patient = user.getPatient();
        patient = null;
        userDBService.saveUser(user);
        user = null;
        Doctor newDoctor = new Doctor(1L,user);
        doctorDBService.saveDoctor(newDoctor);
        doctorJpaRepository.deleteDoctorById(1L);*/
    }

    @Test
    void saveDoctorTest() {
        User user = new User("anatolyFirst", "anatolySecond", "1111", "anatoly", Role.ROLE_USER);
        userDBService.saveUser(user);
        User anatoly = userDBService.findUserByLogin("user");
        doctorDBService.saveDoctor(anatoly);
    }
}