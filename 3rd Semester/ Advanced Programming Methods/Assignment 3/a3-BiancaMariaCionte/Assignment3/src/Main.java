import Domain.*;
import Repository.*;
import Service.Service;
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
        try (FileReader reader = new FileReader("D://a3-BiancaMariaCionte//Assignment3//src//settings.properties")) {
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
                patientRepo = setupTextRepositories(properties);
                appointmentRepo = new AppointmentsRepository();
                break;
            case "binaryFile":
                patientRepo = setupBinaryRepositories(properties);
                appointmentRepo = new AppointmentsRepository();
                //appointmentRepo = setupBinaryRepositories1(properties);
                break;
            default:
                System.out.println("Invalid repository type specified in settings.properties: " + repoType);
                return;
        }


        if (patientRepo != null && appointmentRepo != null) {
            Service service = new Service(patientRepo, appointmentRepo);
            //Service service = new Service(patientRepo.toString(), appointmentRepo.toString());
            Ui ui = new Ui(service);
            ui.run();
        }
    }

    private static PatientFileRepository setupTextRepositories(Properties properties) {
        String patientsFile = properties.getProperty("Patient_repository");

        if (patientsFile == null) {
            System.out.println("Text repository file is not properly specified in settings.properties.");
            return null;
        }

        patientsFile = patientsFile.trim();
        return new PatientFileRepository(patientsFile);
    }

    private static PatientBinaryFileRepository setupBinaryRepositories(Properties properties) {
        String patientsFile = properties.getProperty("Patient_repository");
        //String appointmentsFile = properties.getProperty("Appointment_repository");

        if (patientsFile == null) {
            System.out.println("Binary repository file is not properly specified in settings.properties.");
            return null;
        }

        patientsFile = patientsFile.trim();
        //appointmentsFile = appointmentsFile.trim();
        return new PatientBinaryFileRepository(patientsFile);}

    private static AppointmentBinaryFileRepository setupBinaryRepositories1(Properties properties) {
        String appointmentsFile = properties.getProperty("Appointment_repository");

        if (appointmentsFile == null) {
            System.out.println("Binary repository file is not properly specified in settings.properties.");
            return null;
        }

        appointmentsFile = appointmentsFile.trim();
        return new AppointmentBinaryFileRepository(appointmentsFile);
    }
}



//import Domain.*;
//import Repository.*;
//import Service.Service;
//import Ui.Ui;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws ValidationException, IOException
//    {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter repository type (inmemory, textFile, binaryFile): ");
//        String repoType = scanner.nextLine().trim();
//
//        GenericRepository<Patient, Integer> patientRepo = null;
//        GenericRepository<Appointment, Integer> appRepo = null;
//
//        switch (repoType)
//        {
//            case "textFile":
//                patientRepo = new PatientRepository();
//                appRepo = new AppointmentsRepository();
//                break;
////            case "textFile":
////                System.out.print("Enter the path for the Patient repository file: ");
////                String sourceNamePatient = scanner.nextLine().trim();
////                System.out.print("Enter the path for the Appointment repository file: ");
////                String sourceNameAppointment = scanner.nextLine().trim();
////                patientRepo = new PatientFileRepository(sourceNamePatient);
////                appRepo = new AppointmentFileRepository(sourceNameAppointment);
////                break;
//            case "binaryFile":
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid repository type: " + repoType);
//        }
//
//        if (patientRepo != null && appRepo != null)
//        {
//            Service service = new Service(patientRepo, appRepo);
//            Ui ui = new Ui(service);
//            ui.run();
//        }
//    }
//}
//




//import Domain.Patient;
//import Domain.Appointment;
//import Domain.ValidationException;
////import Repository.Repository;
//import Repository.*;
//import Service.Service;
//import Ui.Ui;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;
//import java.util.Properties;
//
//public class Main {
//    public static void main(String[] args) throws ValidationException, FileNotFoundException {
////        GenericRepository<Patient, Integer> repoP = new PatientRepository();
////        GenericRepository<Appointment, Integer> repoA = new AppointmentsRepository();
//        GenericRepository<Patient, Integer> repo = null;
//
//        GenericRepository<Patient, Integer> PatientRepo = null;
//        GenericRepository<Appointment, Integer> AppRepo = null;
//
//
//
//        try (FileReader fr = new FileReader("D://a3-BiancaMariaCionte//Assignment3//src//settings.properties"))
//        {
//
//            Properties props = new Properties();
//            props.load(fr);
//
//            String repoType = props.getProperty("repository_type");
//            String sourceNamePatient = props.getProperty("Patient_repository");
//            String sourceNameAppointment = props.getProperty("Appointment_repository");
//
////            switch (repoType) {
////                case "textFile":
////                    PatientRepo = new PatientFileRepository(sourceNamePatient);
////                    AppRepo = new AppointmentFileRepository(sourceNameAppointment);
////                    ((PatientFileRepository) PatientRepo).readFromFile(); // Read existing data from the file
////                    ((AppointmentFileRepository) AppRepo).readFromFile(); // Read existing data from the file
////                    Service service = new Service(PatientRepo, AppRepo);
////                    Ui ui = new Ui(service);
////                    ui.run();
////                    break;
////                case "binaryFile":
////                    PatientRepo = new PatientBinaryFileRepository(sourceNamePatient);
////                    AppRepo = new AppointmentBinaryFileRepository(sourceNameAppointment);
////                    ((PatientBinaryFileRepository) PatientRepo).readFromFile(); // Read existing data from the file
////                    ((AppointmentBinaryFileRepository) AppRepo).readFromFile(); // Read existing data from the file
////                    Service service2 = new Service(PatientRepo, AppRepo);
////                    Ui ui2 = new Ui(service2);
////                    ui2.run();
////                    break;
////            }
//
//            switch (repoType)
//            {
////                case "inmemory":
////                    repo = new PatientRepository();
////                    break;
//                case "textFile":
//                    PatientRepo = new PatientFileRepository(sourceNamePatient);
//                    AppRepo = new AppointmentFileRepository(sourceNameAppointment);
//                    Service service = new Service(PatientRepo, AppRepo);
//                    Ui ui = new Ui(service);
//                    ui.run();
//                    break;
//                case "binaryFile":
//                    PatientRepo = new PatientBinaryFileRepository(sourceNamePatient);
//                    AppRepo = new AppointmentBinaryFileRepository(sourceNameAppointment);
//                    Service service2 = new Service(PatientRepo, AppRepo);
//                    Ui ui2 = new Ui(service2);
//                    ui2.run();
//                    break;
//            }
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
////        Service serv = new Service(repoP, repoA);
////        Ui ui = new Ui(serv);
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