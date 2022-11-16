package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.DoctorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorDBService {

    @Autowired
    private DoctorJpaRepository doctorJpaRepository;

    public Doctor saveDoctor(User user) {
        Doctor doctor = new Doctor(user);
        Doctor savedDoctor = doctorJpaRepository.save(doctor);
        return savedDoctor;
    }



}
