package Test;

import Domain.Appointment;
import Domain.Patient;
import Domain.Identifiable;
import Domain.ValidationException;
import Repository.AppointmentsRepository;
import Repository.GenericRepository;
import Repository.PatientRepository;
import Service.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class ServiceTest <T extends Identifiable<Integer>, ID>{
    private Service service;
    private GenericRepository<Patient, Integer> repoPatients;
    private GenericRepository<Appointment, Integer> repoAppointments;

    ///################## exception
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        // Initialize your repositories (you may want to use mocks or real repositories based on your needs)
        repoPatients = new PatientRepository();
        repoAppointments = new AppointmentsRepository();
        // Initialize the service with the repositories
        service = new Service(repoPatients, repoAppointments);
        //service = new Service(repoPatients.toString(), repoAppointments.toString());
    }

    @Test
    public void testAddPatient() throws ValidationException {
        T patient = (T) new Patient(1, "John Doe", "Condition");
        service.addPatient(patient);
        // Verify that the patient is added to the repository
        Assertions.assertTrue(repoPatients.getAll().iterator().hasNext());
    }

    @Test
    public void testGetAllPatients() {
        // Ensure that getAllPatients returns a non-null iterable
        Assertions.assertNotNull(service.getAllPatients());
    }

    @Test
    public void testDeletePatient() throws ValidationException {
        T patient = (T) new Patient(2, "Jane Doe", "Condition");
        service.addPatient(patient);
        // Verify that the patient is initially in the repository
        Assertions.assertTrue(repoPatients.getAll().iterator().hasNext());
        // Delete the patient
        service.deletePatient((ID) patient.getId());
        // Verify that the patient is no longer in the repository
        Assertions.assertFalse(repoPatients.getAll().iterator().hasNext());
    }

    @Test
    public void testUpdatePatient() throws ValidationException {
        T patient = (T) new Patient(3, "Bob", "Condition");
        service.addPatient(patient);

        // Update patient information
        Patient updatedPatient = new Patient(3, "Bob Updated", "New Condition");
        service.updatePatient(updatedPatient);

        // Verify that the patient information is updated
        Assertions.assertEquals(updatedPatient.getName(), ((Patient) repoPatients.findById((Integer) updatedPatient.getId())).getName());
    }

    @Test
    public void testAddAppointment() throws ValidationException {
        T appointment = (T) new Appointment(1, "Checkup", "2023-01-01");
        service.addAppointment(appointment);
        // Verify that the appointment is added to the repository
        Assertions.assertTrue(repoAppointments.getAll().iterator().hasNext());
    }

    @Test
    public void testGetAllAppointments() {
        // Ensure that getAllAppointments returns a non-null iterable
        Assertions.assertNotNull(service.getAllAppointments());
    }

    @Test
    public void testDeleteAppointment() throws ValidationException {
        T appointment = (T) new Appointment(2, "Checkup", "2023-01-01");
        service.addAppointment(appointment);
        // Verify that the appointment is initially in the repository
        Assertions.assertTrue(repoAppointments.getAll().iterator().hasNext());
        // Delete the appointment
        service.deleteAppointment((ID) appointment.getId());
        // Verify that the appointment is no longer in the repository
        Assertions.assertFalse(repoAppointments.getAll().iterator().hasNext());
    }

    @Test
    public void testUpdateAppointment() throws ValidationException {
        T appointment = (T) new Appointment(3, "Checkup", "2023-01-01");
        service.addAppointment(appointment);

        // Update appointment information
        Appointment updatedAppointment = new Appointment(3, "New Checkup", "2023-01-02");
        service.updateAppointment((ID) updatedAppointment.getId(), updatedAppointment);

        // Verify that the appointment information is updated
        Assertions.assertEquals(updatedAppointment.getDescription(),
                ((Appointment) repoAppointments.findById((Integer) updatedAppointment.getId())).getDescription());
    }
}