package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.Doctor;
import by.salov.tms.courseproject.entities.Patient;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.repositories.DoctorJpaRepository;
import by.salov.tms.courseproject.repositories.PatientJpaRepository;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PatientDBService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PatientJpaRepository patientJpaRepository;

    public Patient savePatient(Patient patient) {

        Patient savedPatient = patientJpaRepository.save(patient);
        return savedPatient;
    }

    public Patient savePatientByUserLogin(String login) {
        User userByLogin = userJpaRepository.findUserByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + login + " not found")
        );
        if(userHasPatient(userByLogin)) {
            return userByLogin.getPatient();
        } else {
            Patient newPatient = new Patient(userByLogin);
            Patient savedPatient = patientJpaRepository.save(newPatient);
            return savedPatient;
        }
    }

    public Map<Long,Patient> findAllPatientsMap() {
        Map<Long,Patient> patientsMap = null;
        List<Patient> all = patientJpaRepository.findAll();
        patientsMap = all.stream().collect(Collectors.toMap(Patient::getId, patient -> patient));
        return patientsMap;
    }
    private boolean userHasPatient(User user) {
        if(user.getPatient() == null) {
            return false;
        }
        return true;
    }
}
