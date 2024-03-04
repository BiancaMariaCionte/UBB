package Test;

import Domain.Appointment;
import Domain.Patient;
import Domain.Identifiable;
import Domain.ValidationException;
import Repository.GenericRepository;
import Repository.MemoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemoryRepositoryTest {
    private GenericRepository<Patient, Integer> patientRepository;
    private GenericRepository<Appointment, Integer> appointmentRepository;

    @BeforeEach
    public void setUp() {
        patientRepository = new MemoryRepository<>();
        appointmentRepository = new MemoryRepository<>();
    }

    @Test
    public void testAddEntity() throws ValidationException {
        Appointment appointment = new Appointment(1, "Dental Checkup", "2023-12-01");
        patientRepository.addEntity(new Patient(1, "John Doe", "Dentistry"));

        Assertions.assertEquals(1, patientRepository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testDeleteEntity() throws ValidationException {
        Appointment appointment = new Appointment(1, "Dental Checkup", "2023-12-01");
        Patient patient = new Patient(1, "John Doe", "Dentistry");
        patientRepository.addEntity(patient);
        patientRepository.deleteEntity(1);

        Assertions.assertEquals(0, patientRepository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFindById() throws ValidationException {
        Appointment appointment = new Appointment(1, "Dental Checkup", "2023-12-01");
        Patient patient = new Patient(1, "John Doe", "Dentistry");
        patientRepository.addEntity(patient);

        Patient foundPatient = patientRepository.findById(1);

        Assertions.assertEquals(patient, foundPatient);
    }

    @Test
    public void testUpdateEntity() throws ValidationException {
        Appointment oldAppointment = new Appointment(1, "Dental Checkup", "2023-12-01");
        Patient patient = new Patient(1, "John Doe", "Dentistry");
        patientRepository.addEntity(patient);

        Appointment newAppointment = new Appointment(2, "General Checkup", "2024-01-15");
        Patient updatedPatient = new Patient(1, "John Doe", "Dentistry");
        patientRepository.updateEntity(1, updatedPatient);

        Assertions.assertEquals(1, patientRepository.getAll().spliterator().getExactSizeIfKnown());
       // Assertions.assertEquals(newAppointment, patientRepository.findById(1).getAppointments()[0]);
    }
}