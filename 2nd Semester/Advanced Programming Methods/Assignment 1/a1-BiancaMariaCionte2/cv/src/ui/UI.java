package ui;

import domain.Patient;
import service.Service;

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
            if(command==0||command==1 || command== 2)
            {
            switch (command) {
                case 0:
                    return;
                case 1:
                    listPatients();
                    break;
                case 2:
                    deletePatient();
                    break;

            }}
            else {
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
}
