import Domain.*;
import Repository.*;
import Service.*;
import Ui.Ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ValidationException, IOException
    {


        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        try (FileReader reader = new FileReader("D://a4-BiancaMariaCionte//Assignment4//src//settings.properties")) {
            properties.load(reader);
        }
        catch (IOException e)
        {
            System.out.println("Error reading properties file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("Properties loaded: " + properties);

        String repoType = properties.getProperty("repository_type");
// ##########
        String location = properties.getProperty("Location");
        // ##########
        if (repoType == null) {
            System.out.println("Repository type is not specified in settings.properties.");
            return;
        }

        repoType = repoType.trim();

        GenericRepository<Patient, Integer> patientRepo = null;
        GenericRepository<Appointment, Integer> appointmentRepo = null;


        switch (repoType) {
            case "inmemory":
                patientRepo = new PatientRepository();
                appointmentRepo = new AppointmentsRepository();
                break;
            case "textFile":
                patientRepo = setupTextRepositoriesP(properties);
                appointmentRepo = setupTextRepositoriesA(properties);
                break;
            case "binaryFile":
                patientRepo = setupBinaryRepositories(properties);
                appointmentRepo = setupBinaryRepositoriesA(properties);
                break;
                //#####
            case "dataBase":
                patientRepo = setupDatabaseRepositoriesP(properties);
                appointmentRepo = setupDatabaseRepositoriesA(properties);
                break;
                ///#####
            case "json":
                patientRepo = setupJSONRepositoryP(properties);
                appointmentRepo = setupJSONRepositoryA(properties);
                break;
            case "xml":
                patientRepo = setupXMLRepositoriesP(properties);
                appointmentRepo = setupXMLRepositoriesA(properties);
                break;
            default:
                System.out.println("Invalid repository type specified in settings.properties: " + repoType);
                return;
        }

        //Create ReportService
        ReportService reportService = new ReportService(patientRepo, appointmentRepo);


        // Display reports
        reportService.displayReports();


        if (patientRepo != null && appointmentRepo != null) {
            Service service = new Service(patientRepo, appointmentRepo);
            Ui ui = new Ui(service);
            ui.run();


        }
//
    }

    // SET UP TEXT REPOSITORIES:
    // PATIENT
    private static PatientFileRepository setupTextRepositoriesP(Properties properties) {
        String patientsFile = properties.getProperty("Patient_repository");

        if (patientsFile == null) {
            System.out.println("Text repository file is not properly specified in settings.properties.");
            return null;
        }

        patientsFile = patientsFile.trim();
        return new PatientFileRepository(patientsFile);
    }

    //APPOINTMENT
    private static AppointmentFileRepository setupTextRepositoriesA(Properties properties) {
        String appointmentsFile = properties.getProperty("Appointment_repository");

        if (appointmentsFile == null) {
            System.out.println("Text repository file is not properly specified in settings.properties.");
            return null;
        }

        appointmentsFile = appointmentsFile.trim();
        return new AppointmentFileRepository(appointmentsFile);
    }

    //SETTING UP BINARY FILE REPOSITORIES:
    //PATIENT
    private static PatientBinaryFileRepository setupBinaryRepositories(Properties properties) {
        String patientsFile = properties.getProperty("Patient_repository");

        if (patientsFile == null) {
            System.out.println("Binary repository file is not properly specified in settings.properties.");
            return null;
        }

        patientsFile = patientsFile.trim();
        return new PatientBinaryFileRepository(patientsFile);}

    //APPOINTMENT
    private static AppointmentBinaryFileRepository setupBinaryRepositoriesA(Properties properties) {
        String appointmentsFile = properties.getProperty("Appointment_repository");

        if (appointmentsFile == null) {
            System.out.println("Binary repository file is not properly specified in settings.properties.");
            return null;
        }

        appointmentsFile = appointmentsFile.trim();
        return new AppointmentBinaryFileRepository(appointmentsFile);
    }

    // SET UP DATABASE REPOSITORIES:
// PATIENT
    private static PatientDBRepository setupDatabaseRepositoriesP(Properties properties) {
        String repositoryType = properties.getProperty("Patient_repository");
        //String location = properties.getProperty("Location");

        if (repositoryType == null) {
            System.out.println("Database repository settings are not properly specified in settings.properties.");
            return null;
        }

        return new PatientDBRepository(repositoryType);
    }

    // APPOINTMENT
    private static AppointmentDBRepository setupDatabaseRepositoriesA(Properties properties) {
        String repositoryType = properties.getProperty("Appointment_repository");
       // String location = properties.getProperty("Location");

        if (repositoryType == null) {
            System.out.println("Database repository settings are not properly specified in settings.properties.");
            return null;
        }

        return new AppointmentDBRepository(repositoryType);
    }

    // SET UP JSON REPOSITORIES:
    // PATIENT
    private static PatientJSONRepository setupJSONRepositoryP(Properties properties) {
        //String location = properties.getProperty("Location");
        String patientsFile = properties.getProperty("Patient_repository");

        if ( patientsFile == null) {
            System.out.println("JSON repository settings are not properly specified in settings.properties.");
            return null;
        }

        return new PatientJSONRepository(patientsFile);
    }

    // APPOINTMENT
    private static AppointmentJSONRepository setupJSONRepositoryA(Properties properties) {
       // String location = properties.getProperty("Location");
        String appointmentsFile = properties.getProperty("Appointment_repository");

        if ( appointmentsFile == null) {
            System.out.println("JSON repository settings are not properly specified in settings.properties.");
            return null;
        }

        return new AppointmentJSONRepository( appointmentsFile);
    }

    // SET UP XML REPOSITORIES:
    //PATIENT

    private static PatientXMLRepository setupXMLRepositoriesP(Properties properties) {
        String patientsFile = properties.getProperty("Patient_repository");

        if (patientsFile == null) {
            System.out.println("XML repository file is not properly specified in settings.properties.");
            return null;
        }

        patientsFile = patientsFile.trim();
        return new PatientXMLRepository(patientsFile);
    }


    //APPOINTMENT
    private static AppointmentXMLRepository setupXMLRepositoriesA(Properties properties) {
        String appointmentsFile = properties.getProperty("Appointment_repository");

        if (appointmentsFile == null) {
            System.out.println("XML repository file is not properly specified in settings.properties.");
            return null;
        }

        appointmentsFile = appointmentsFile.trim();
        return new AppointmentXMLRepository(appointmentsFile);
    }

}






//
////        GenericRepository<Patient, Integer> patientRepository = new Repository.MemoryRepository<Patient>();
//
//        Appointment a1 = new Appointment(2000, "Monthly check for cancer.", "23/06/2024");
//        Appointment a2 = new Appointment(2001, "Weekly check for diabetes.", "10/11/2024");
//        Appointment a3 = new Appointment(2002, "Annual ophthalmological consultation.", "27/03/2024");
//        Appointment a4 = new Appointment(2003, "Dermatological control for acne.", "01/02/2024");
//        Appointment a5 = new Appointment(2004, "Routine consultation for scoliosis.", "24/05/2024");
//
////        Patient p1 = new Patient(1000, "Cionte Bianca", "27/03/2004", new Appointment[]{a1,a2});
////        Patient p2 = new Patient(1001, "Pavel Andra", "15/05/2003", new Appointment[]{a2, a3});
////        Patient p3 = new Patient(1002, "Pop Cristina", "20/05/2003", new Appointment[]{a3, a4});
////        Patient p4 = new Patient(1003, "Buruian Amalia", "31/07/2003", new Appointment[]{a5, a3});
////        Patient p5 = new Patient(1004, "Ioan Mircea", "13/10/2000", new Appointment[]{a2, a3});
////        Patient p6 = new Patient(1005, "Grajdan Mihaela", "03/10/2003", new Appointment[]{a2, a4});
////        Patient p7 = new Patient(1006, "Onea Andrei", "10/08/2002", new Appointment[]{a1, a5});
////        Patient p8 = new Patient(1007, "Bercu Iulia", "23/01/2000", new Appointment[]{a1, a3});
//
//// 440
////        serv.addPatient(p1);
////        serv.addPatient(p2);
////        serv.addPatient(p3);
////        serv.addPatient(p4);
////        serv.addPatient(p5);
////        serv.addPatient(p6);
////        serv.addPatient(p7);
////        serv.addPatient(p8);
////
////
////        serv.addAppointment(a1);
////        serv.addAppointment(a2);
////        serv.addAppointment(a3);
////        serv.addAppointment(a4);
////        serv.addAppointment(a5);
////
////
////        ui.run();
//    }
//}