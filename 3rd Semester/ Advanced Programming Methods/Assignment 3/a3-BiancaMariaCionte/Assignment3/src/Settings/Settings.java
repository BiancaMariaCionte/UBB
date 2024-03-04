package Settings;

import javax.management.RuntimeErrorException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {
    private Properties properties;
    private final String filename = "D://a3-BiancaMariaCionte//Assignment3//src//settings.properties";

    public Settings() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRepositoryType() {
        return properties.getProperty("Repository");
    }

    public String getPatientFile() {
        return properties.getProperty("Patients");
    }

    public String getAppointmentFile() {
        return properties.getProperty("Appointments");
    }

    public void updateRepositoryType(String repositoryType) {
        properties.setProperty("Repository", repositoryType);
        savePropertiesToFile();
    }

    public void updateCakesFile(String patientsFile) {
        properties.setProperty("Patients", patientsFile);
        savePropertiesToFile();
    }

    public void updateOrdersFile(String appointmentsFile) {
        properties.setProperty("Appointments", appointmentsFile);
        savePropertiesToFile();
    }

    private void savePropertiesToFile() {
        try (OutputStream outputStream = new FileOutputStream(filename)) {
            properties.store(outputStream, "Updated settings");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
