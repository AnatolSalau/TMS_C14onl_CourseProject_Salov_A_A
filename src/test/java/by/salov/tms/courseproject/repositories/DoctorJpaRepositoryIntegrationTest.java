package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.dao.DoctorDBService;
import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.Patient;
import by.salov.tms.courseproject.entities.User;
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
        Doctor doctorById = doctorJpaRepository.findDoctorById(1L);
        User user = doctorById.getUser();
        Patient patient = user.getPatient();
        patient = null;
        userDBService.saveUser(user);
        user = null;
        Doctor newDoctor = new Doctor(1L,user);
        doctorDBService.saveDoctor(newDoctor);
        doctorJpaRepository.deleteDoctorById(1L);
    }
}