package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.roles.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface DoctorJpaRepository extends JpaRepository<Doctor,Long> {
    void deleteDoctorById(Long id);
    Doctor findDoctorById(Long id);
}
