package repository;


import domain.Patient;

import java.io.IOError;
import java.util.ArrayList;
import java.util.Iterator;

public class Repository implements RepoInterface{
    private ArrayList<Patient> patients = new ArrayList<>();

    @Override
    public void deletePatient(int patientId) {
        Iterator<Patient> iterator = patients.iterator();

        try
        {
            while (iterator.hasNext()) {
                Patient patient = iterator.next();
                if (patient.getId() == patientId) {
                    iterator.remove();
                }
            }
        }catch(IOError e) {
            System.out.println("Something went wrong.");
        }



    }

    @Override
    public ArrayList<Patient> getAllPatients() {
        return this.patients;
    }

    @Override
    public void addPatients(Patient patient) {
        patients.add(patient);

    }

    @Override
    public void updatePatient(Patient patient) {

    }

    public static void main(String[] args) {
        RepoInterface repoInterface = new Repository();
        //RepoInterface repo = new AmRepo();
    }
}