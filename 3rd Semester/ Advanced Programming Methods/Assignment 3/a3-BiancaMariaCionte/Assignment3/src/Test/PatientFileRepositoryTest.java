package Test;

import Domain.Appointment;
import Domain.Patient;
import Domain.ValidationException;
import Repository.PatientFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class PatientFileRepositoryTest {
    private PatientFileRepository patientFileRepository;

    @BeforeEach
    public void setUp() {
        patientFileRepository = new PatientFileRepository("D://a3-BiancaMariaCionte//Assignment3//src//testPatients.txt");
    }

    @Test
    public void testReadFromFile() throws ValidationException {
//        // Creating a temporary file with sample patient data for testing
//        patientFileRepository = new PatientFileRepository("D://a3-BiancaMariaCionte//Assignment3//src//testPatients.txt");
//
//        // Reading from the file and testing if the patient is correctly added to the repository
//       // patientFileRepository.readFromFile();
//        List<Patient> patients = readPatientsFromFile("D://a3-BiancaMariaCionte//Assignment3//src//testPatients.txt");
//
//        Assertions.assertEquals(0, patients.size());
//        Patient expectedPatient = new Patient(1, "John Doe", "Dentistry");
//        //Assertions.assertEquals(expectedPatient, patients.get(0));
    }

    void addItem() throws ValidationException
    {
        Patient p1 = new Patient(1010, "Bibi", "cancer");
        Patient p2 = new Patient(1010, "Bibi", "cancer");
        patientFileRepository.addEntity(p1);
        Iterable<Patient> listPatients = patientFileRepository.getAll();
        boolean found = false;
        for(Patient patient: listPatients)
        {
            if(patient.getId()==1010)
                found = true;
        }
        assertTrue(found);
        try{
            patientFileRepository.addEntity(p2);
        }catch (ValidationException e)
        {
            System.out.println("Item already exists.");
        }
    }

    @Test
    void updateById() throws ValidationException
    {
       Patient patientToUpdate = new Patient(1010,"Bibi", "cancer");
       patientFileRepository.updateEntity(1010, patientToUpdate);
       assertEquals("Bibi",patientFileRepository.findById(1010).getName());
    }

    @Test
    void getAllItems() throws ValidationException
    {
        Iterable<Patient> listOfPatients = patientFileRepository.getAll();
        int counter = 0;
        for(Patient patient:listOfPatients)
        {
            counter++;
        }
        //assert counter == 2;
    }

    @Test
    void deleteEntity() {
        try {
            Patient patient = new Patient(1, "John Doe", "Fever");
            patientFileRepository.addEntity(patient);

            patientFileRepository.deleteEntity(1);

            assertFalse((BooleanSupplier) patientFileRepository.findById(1));
        } catch (ValidationException e) {
            //fail("Exception not expected: " + e.getMessage());
        }
    }


//    @Test
//    public void testWriteToFile() throws ValidationException {
//        // Adding a patient to the repository and writing to the file
//        Appointment appointment = new Appointment(1, "Dental Checkup", "2023-12-01");
//        Patient patient = new Patient(1, "John Doe", "Dentistry");
//        patientFileRepository.addEntity(patient);
//
//        // Reading from the file and testing if the patient is correctly written to the file
//        patientFileRepository.writeToFile();
//        List<Patient> patients = readPatientsFromFile("D://a3-BiancaMariaCionte//Assignment3//src//testPatients.txt");
//
//        Assertions.assertEquals(0, patients.size());
//        Patient expectedPatient = new Patient(1, "John Doe", "Dentistry");
//        //Assertions.assertEquals(expectedPatient, patients.get(0));
//    }
//
//    // Helper method to create a test file with specified content
//    private void createTestFile(String filename, String content) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//            writer.write(content);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // Helper method to read patients from the file
//    private List<Patient> readPatientsFromFile(String filename) {
//        List<Patient> patients = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] tokens = line.split("\\|");
//                if (tokens.length != 3) {
//                    // Skip invalid lines
//                    continue;
//                }
//                Integer id = Integer.parseInt(tokens[0].trim());
//                String name = tokens[1].trim();
//                String disease = tokens[2].trim();
//
//                Patient patient = new Patient(id, name, disease);
//                patients.add(patient);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return patients;
//    }
}