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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorDBService {

    @Autowired
    private DoctorJpaRepository doctorJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PatientJpaRepository patientJpaRepository;

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
    public Map<Long,Doctor> findAllDoctorsMap() {
        Map<Long,Doctor> doctorsMap = null;
        List<Doctor> all = doctorJpaRepository.findAll();
        doctorsMap = all.stream().collect(Collectors.toMap(Doctor::getId, doctor -> doctor));
        return doctorsMap;
    }

    public void addPatientToDoctor(String patientLogin, String doctorLogin) {
        User userPatientFromDB = userJpaRepository.findUserByLogin(patientLogin).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + patientLogin + " not found")
        );
        User userDoctorFromDB = userJpaRepository.findUserByLogin(doctorLogin).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + doctorLogin + " not found")
        );
        Patient patientFromDB = userPatientFromDB.getPatient();
        Doctor doctorFromDB = userDoctorFromDB.getDoctor();
        if (patientFromDB != null && doctorFromDB != null) {
            patientFromDB.getDoctors().add(doctorFromDB);
            doctorJpaRepository.save(doctorFromDB);
        }
    }
    public Map<Long,Patient> getAllPatientsFromDoctor(String userLogin) {
        User userByLogin = userJpaRepository.findUserByLogin(userLogin).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + userLogin + " not found")
        );
        Set<Patient> patientSet = userByLogin.getDoctor().getPatients();
        Map<Long, Patient> patientMap = patientSet.stream()
                .collect(Collectors.toMap(Patient::getId, patient -> patient));
        return patientMap;
    }

    public void deleteDoctorFromUser(String userLogin) {
        User userByLogin = userJpaRepository.findUserByLogin(userLogin).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + userLogin + " not found")
        );
        Doctor doctor = userByLogin.getDoctor();
        if(doctor != null) {
            Set<Patient> doctorPatients = doctor.getPatients();
                for (Patient patient : doctorPatients) {
                    patient.getDoctors().remove(doctor);
                    patientJpaRepository.save(patient);
                }
            userByLogin.setDoctor(null);
            userJpaRepository.save(userByLogin);
            doctorJpaRepository.delete(doctor);
        }
    }

    private boolean userHasDoctor(User user) {
        if(user.getDoctor() == null) {
            return false;
        }
        return true;
    }

}
