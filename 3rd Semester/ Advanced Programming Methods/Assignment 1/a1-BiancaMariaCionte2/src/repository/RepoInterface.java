package src.repository;

import src.domain.Patient;

import java.util.ArrayList;

public interface RepoInterface {
    ArrayList<Patient> getAllPatients();

    void addPatients(Patient patient);

    void updatePatient(Patient patient);

    void deletePatient(int idPatient);

}
