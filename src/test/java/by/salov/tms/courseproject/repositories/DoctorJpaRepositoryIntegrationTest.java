package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorJpaRepositoryIntegrationTest {

    @Autowired
    DoctorJpaRepository doctorJpaRepository;

    @Test
    void deleteDoctorByIdTest() {
        Doctor doctorById = doctorJpaRepository.findDoctorById(1L);
        User user = doctorById.getUser();
        user = null;

        doctorJpaRepository.deleteDoctorById(1L);
    }
}