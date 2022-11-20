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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientDBService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PatientJpaRepository patientJpaRepository;

    @Autowired
    private DoctorJpaRepository doctorJpaRepository;

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

    public void addDoctorToPatient(String doctorLogin, String patientLogin) {

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
            patientJpaRepository.save(patientFromDB);
        }
    }

    public Map<Long,Doctor> getAllDoctorsFromPatient(String userLogin) {
        User userByLogin = userJpaRepository.findUserByLogin(userLogin).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + userLogin + " not found")
        );
        Set<Doctor> doctorSet = userByLogin.getPatient().getDoctors();
        Map<Long, Doctor> doctorMap = doctorSet.stream()
                .collect(Collectors.toMap(Doctor::getId, doctor -> doctor));
        return doctorMap;
    }

    public void deletePatientFromUser(String userLogin) {
        User userByLogin = userJpaRepository.findUserByLogin(userLogin).orElseThrow(
                () -> new UsernameNotFoundException("User with login: " + userLogin + " not found")
        );
        Patient patient = userByLogin.getPatient();
        if(patient != null) {
            Set<Doctor> doctors = patient.getDoctors();
            for (Doctor doctor : doctors) {
                doctor.getPatients().remove(patient);
                doctorJpaRepository.save(doctor);
            }
            userByLogin.setPatient(null);
            userJpaRepository.save(userByLogin);
            patientJpaRepository.delete(patient);
        }
    }
    private boolean userHasPatient(User user) {
        if(user.getPatient() == null) {
            return false;
        }
        return true;
    }
}
