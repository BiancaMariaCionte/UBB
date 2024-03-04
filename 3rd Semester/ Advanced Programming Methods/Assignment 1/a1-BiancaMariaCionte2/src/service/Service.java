package src.service;

import src.domain.Appointment;
import src.domain.Patient;
import src.repository.AppointmentsRepository;
import src.repository.Repository;

import java.util.ArrayList;

public class Service {
    private Repository repo;

    private AppointmentsRepository appRepo;

    public Service(Repository r)
    {
        this.repo = r;
    }

    public void addPatient(int id, String name, String dateOfBirth)
    {
        Patient d = new Patient(id, name, dateOfBirth);
        this.repo.addPatients(d);
    }

    public ArrayList<Patient> getAll()
    {
        return this.repo.getAllPatients();
    }
    public void deletePatient(int patientId) {
        // Check if the patient with the specified ID exists in the repository
        ArrayList<Patient> patients = this.repo.getAllPatients();
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                // If found, remove the patient from the repository
                patients.remove(patient);
                return;  // Exit the loop once the patient is deleted
            }
        }
        // If the patient is not found, you can choose to display an error message or handle it as needed.
    }

    public void updatePatient(int patientId, String newName, String newDateOfBirth) {
        ArrayList<Patient> patients = this.repo.getAllPatients();
        for (int i=0; i<patients.size();i++)
        {
            Patient patient = patients.get(i);
            if(patient.getId()==patientId)
            {
            patient.setName(newName);
            patient.setDateOfBirth(newDateOfBirth);
            repo.updatePatient(patient);
            }

        }
        // If the patient is not found, you can choose to display an error message or handle it as needed.
    }

    public void cancelAppointment(int appId)
    {
        appRepo.cancelAppointment(appId);
    }

    public void addAppointment(Appointment app)
    {
        //Appointment app =new Appointment(id, date,description)
        appRepo.addAppointment(app);
    }
}
