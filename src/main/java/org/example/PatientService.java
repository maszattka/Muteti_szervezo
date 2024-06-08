package org.example;

import org.example.PatientDAO;
import org.example.Patient;

import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO = new PatientDAO();

    public void addPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }

    public Patient getPatientByTaj(String taj) {
        return patientDAO.getPatientByTaj(taj);
    }

    public boolean deletePatient(String taj) {
        return patientDAO.deletePatient(taj);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }
}
