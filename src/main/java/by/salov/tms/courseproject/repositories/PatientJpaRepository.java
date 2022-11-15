package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpaRepository extends JpaRepository<Patient, Long> {

}
