package src.ui;

import src.domain.Patient;
import src.service.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Service serv;

    public UI(Service serv) {
        this.serv = serv;
    }

    public void listPatients() {
        ArrayList<Patient> patients = this.serv.getAll();
        for (Patient d : patients)
            System.out.println(d);
    }

    public void printMenu() {
        System.out.println("4 - Cancel an appointment.");
        System.out.println("3 - Update a patient.");
        System.out.println("2 - Delete a patient by ID");
        System.out.println("1 - List all patients");
        System.out.println("0 - Exit");
    }

    public void run() {
        while (true) {
            printMenu();
            System.out.print("Please input your option: ");
            Scanner scan = new Scanner(System.in);
            int command = scan.nextInt();
            if (command == 0 || command == 1 || command == 2 || command == 3|| command ==4) {
                switch (command) {
                    case 0:
                        return;
                    case 1:
                        listPatients();
                        break;
                    case 2:
                        deletePatient();
                        break;
                    case 3:
                        updatePatient();
                        break;
                    case 4:
                        System.out.println("Enter the id of the appointment you want to cancel: ");
                        int id = scan.nextInt();
                        serv.cancelAppointment(id);
                        break;

                }
            } else {
                System.out.println("Not a valid choice.");
                System.out.println("Please input your option: ");
                scan = new Scanner(System.in);
            }
        }
    }


    public void deletePatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the patient to delete: ");
        int patientId = scanner.nextInt();

        // Use the PatientService to delete the patient
        serv.deletePatient(patientId);

        System.out.println("Patient with ID " + patientId + " deleted successfully.");
    }

    private ArrayList<Integer> patientsId()
    {
        ArrayList<Patient> patients = this.serv.getAll();
        ArrayList<Integer> ids = new ArrayList<>();
        for(Patient patient:patients)
        {
            ids.add(patient.getId());
        }
        return ids;
    }

    public void updatePatient() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
        System.out.print("Enter the ID of the patient to update: ");
        int patientId = scanner.nextInt();
        ArrayList<Patient> patients = this.serv.getAll();

        for(int i=0;i<patients.size(); i++ )
        {
            Patient patient = patients.get(i);
            if(patient.getId() == patientId){
                System.out.println("Enter the new name: ");

                String newPatientName = scanner.next();

                System.out.println("Enter new date of birth: ");
                String newDateOfBirth = scanner.next();

                serv.updatePatient(patientId, newPatientName, newDateOfBirth);
                break;
            }
        }
        //for(Patient patient : patients)
        //{
        //
        //    if (patient.getId() != patientId)
        //    {
        //        System.out.println("Enter a valid ID!!!");
        //    }

    }
}
