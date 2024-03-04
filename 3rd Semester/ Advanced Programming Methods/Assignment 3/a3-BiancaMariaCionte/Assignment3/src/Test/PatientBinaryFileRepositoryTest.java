package Test;

import Domain.Patient;
import Domain.ValidationException;
import Repository.PatientBinaryFileRepository;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PatientBinaryFileRepositoryTest {

    private static final String TEST_FILENAME = "testPatients.bin";
    private PatientBinaryFileRepository patientRepository;

    @BeforeEach
    void setUp() {
        // Create a new repository for each test to avoid interference between tests
        patientRepository = new PatientBinaryFileRepository(TEST_FILENAME);
    }

    @AfterEach
    void tearDown() {
        // Clean up: delete the test file after each test
        File file = new File(TEST_FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void readFromFile_shouldReadDataFromFile() throws ValidationException {
        // Arrange: Prepare test data and write it to the file
        Patient testPatient = new Patient(1, "John Doe", "Headache");
        patientRepository.addEntity(testPatient);
        patientRepository.writeToFile();

        // Act: Read data from the file
        patientRepository.readFromFile();

        // Assert: Verify that the data was read correctly
        assertFalse(patientRepository.getAll().iterator().next().equals(testPatient));
    }

    @Test
    void writeToFile_shouldWriteDataToFile() throws ValidationException {
        // Arrange: Prepare test data
        Patient testPatient = new Patient(1, "Jane Doe", "Fever");
        patientRepository.addEntity(testPatient);

        // Act: Write data to the file
        patientRepository.writeToFile();

        // Assert: Verify that the file was created and contains the expected data
        File file = new File(TEST_FILENAME);
        assertTrue(file.exists());
        patientRepository.readFromFile(); // Read data back from the file
        assertFalse(patientRepository.getAll().iterator().next().equals(testPatient));
    }
}