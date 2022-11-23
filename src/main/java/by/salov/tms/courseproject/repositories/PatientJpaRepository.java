package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.Patient;
import by.salov.tms.courseproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * SpringDataJPA repository for getting Patient entity */

public interface PatientJpaRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAll();
}
