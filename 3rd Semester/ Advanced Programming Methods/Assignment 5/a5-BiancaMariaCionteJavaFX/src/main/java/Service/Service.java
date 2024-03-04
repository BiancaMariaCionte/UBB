package Service;

import Domain.Identifiable;
import Domain.ValidationException;
import Repository.GenericRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Service<T extends Identifiable<ID>, ID> {
    private final GenericRepository<T, ID> repoPatients;
    private final GenericRepository<T, ID> repoAppointments;

//    public Service(String filenameCarRepo, String filenameReservationRepo) throws FileNotFoundException {
//        if (filenameCarRepo.contains(".txt"))
//            repoPatients = (GenericRepository<T, ID>) new PatientFileRepository(filenameCarRepo);
//        else if (filenameCarRepo.contains(".bin"))
//            repoPatients = (GenericRepository<T, ID>) new PatientBinaryFileRepository(filenameCarRepo);
//        else
//            throw new FileNotFoundException("File must be either .txt or .bin!");
//
//        if (filenameReservationRepo.contains(".txt"))
//            repoAppointments = (GenericRepository<T, ID>) new AppointmentFileRepository(filenameReservationRepo);
//        else if (filenameReservationRepo.contains(".bin"))
//            repoAppointments = (GenericRepository<T, ID>) new AppointmentBinaryFileRepository(filenameReservationRepo);
//        else
//            throw new FileNotFoundException("File must be either .txt or .bin!");
//    }
//


    public Service(GenericRepository<T, ID> rP, GenericRepository<T, ID> rA) {
        this.repoPatients = rP;
        this.repoAppointments = rA;
    }

    public static String getRepositoryType(String filePath) throws IOException {
        try (FileReader fr = new FileReader(filePath)) {
            Properties props = new Properties();
            props.load(fr);
            return props.getProperty("repository_type").trim();
        }
    }

    public void addPatient(T patient) throws ValidationException {
        repoPatients.addEntity(patient);
       // repoPatients.add(patient); // Update the in-memory data
    }

    public Iterable<T> getAllPatients() {
        return repoPatients.getAll();
    }

    public void deletePatient(ID patientId) throws ValidationException {
        repoPatients.deleteEntity(patientId);
    }

    public void updatePatient(T updatedPatient) throws ValidationException {
        repoPatients.updateEntity(updatedPatient.getId(), updatedPatient);
    }

    public void addAppointment(T appointment) throws ValidationException {
        repoAppointments.addEntity(appointment);
    }

    public Iterable<T> getAllAppointments() {
        return repoAppointments.getAll();
    }

    public void deleteAppointment(ID appId) throws ValidationException {
        repoAppointments.deleteEntity(appId);
    }

    public void updateAppointment(ID appId, T updatedAppointment) throws ValidationException {
        repoAppointments.updateEntity(appId, updatedAppointment);
    }
}
