package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.DoctorJpaRepository;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorDBService {

    @Autowired
    private DoctorJpaRepository doctorJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    public Doctor saveDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorJpaRepository.save(doctor);
        return savedDoctor;
    }
    public Doctor saveDoctorByUserLogin(String login) {
        User userByLogin = userJpaRepository.findUserByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + login + " not found")
        );
        if(userHasDoctor(userByLogin)) {
            return userByLogin.getDoctor();
        } else {
            Doctor newDoctor = new Doctor(userByLogin);
            Doctor savedDoctor = doctorJpaRepository.save(newDoctor);
            return savedDoctor;
        }
    }
    private boolean userHasDoctor(User user) {
        if(user.getDoctor() == null) {
            return false;
        }
        return true;
    }

}
