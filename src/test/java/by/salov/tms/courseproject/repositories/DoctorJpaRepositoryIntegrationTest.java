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
/*        Doctor doctorById = doctorJpaRepository.findDoctorById(3L);
        doctorById.setUser(null);
        doctorJpaRepository.save(doctorById);*/
        doctorJpaRepository.deleteDoctorById(2L);
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
/*        User user = new User("anatolyFirst", "anatolySecond", "1111", "anatoly", Role.ROLE_USER);
        userDBService.saveUser(user);
        User anatoly = userDBService.findUserByLogin("anatoly");
        doctorDBService.saveDoctor(anatoly);*/

       User user = new User("vikaFirst", "vikaSecond", "1111", "vika", Role.ROLE_USER);
        userDBService.saveUser(user);
        User vika = userDBService.findUserByLogin("vika");
        Doctor doctor = new Doctor(vika);
        doctorDBService.saveDoctor(doctor);
    }

    @Test
    void deleteDoctorByUser_LoginTest() {
        User vika = userDBService.findUserByLogin("vika");
        Doctor doctorById = doctorJpaRepository.findDoctorByUser(vika);
        doctorById.setUser(null);
        doctorJpaRepository.save(doctorById);
    };
}