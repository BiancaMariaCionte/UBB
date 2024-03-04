//package com.example.a5biancamariaciontejavafx;
//
//import Domain.Appointment;
//import Domain.Patient;
//import Domain.ValidationException;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import Repository.*;
//import Service.*;
//
//import java.time.DateTimeException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Stack;
//
//public class PatientsAppointmentsController {
//    private Service service;
//    private ReportService reportService;
//
//
//    GenericRepository<Patient,Integer> patientIntegerIRepository;
//
//
//
//    @FXML
//    private ListView<Patient> patientsListView;
//
//    @FXML
//    private ListView<Appointment> appointmentsListView;
//
//    @FXML
//    private TextField patientSearchTextField;
//
//    @FXML
//    private TextField appointmentSearchTextField1;
//
//    @FXML
//    private TextField cityTextField;
//
//    @FXML
//    private TextField idTextField;
//
//    @FXML
//    private TextField nameTextField;
//
//    @FXML
//    private TextField phoneNumberTextField;
//
//    @FXML
//    private Button patientAddButton;
//
//    @FXML
//    private Button patientDeleteButton;
//
//    @FXML
//    private Button patientUpdateButton;
//
//    @FXML
//    private Button appointmentAddButton;
//
//    @FXML
//    private Button appointmentDeleteButton;
//
//    @FXML
//    private Button appointmentUpdateButton;
//
//    @FXML
//    private TextField appointmentIdTextField;
//
//    @FXML
//    private TextField dateTextField;
//
//    @FXML
//    private TextField patientNameTextField;
//
//    @FXML
//    private TextField patientIdTextField;
//
//    @FXML
//    private TextField patientCityTextField;
//
//    @FXML
//    private TextField patientPhoneNumberTextField;
//
//    @FXML
//    private Button binaryButton;
//
//    @FXML
//    private Button fileButton;
//
//    @FXML
//    private Button inMemoryButton;
//
//    @FXML
//    private Button databaseButton;
//
//    @FXML
//    private TextField currentRepositoryTextField;
//
//    @FXML
//    private Label currentRepositoyLabel;
//
//    @FXML
//    private Button redoButton;
//
//    @FXML
//    private Button undoButton;
//
//    @FXML
//    private Button undoPatientsButton;
//
//    @FXML
//    private Button redoPatientsButton;
//
//    @FXML
//    private TextField patientNameStartingTextField;
//
//    @FXML
//    private TextField phoneNumberIdTextField;
//
//    @FXML
//    private Label foundPhoneNumber;
//
//    @FXML
//    private TextField dateBefore;
//
//
//    public PatientsAppointmentsController(Service dentalService) {
//        this.service = dentalService;
//    }
//
//    void populatePatientList() {
//
//        Collection<Patient> patientsCollection = (Collection<Patient>) service.getAllPatients();
//        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(patientsCollection);
//        patientsListView.setItems(patientObservableList);
//    }
//
//    void populateAppointmentList() {
//        Collection<Appointment> appointmentsCollection = (Collection<Appointment>) service.getAllAppointments();
//        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList(appointmentsCollection);
//        appointmentsListView.setItems(appointmentObservableList);
//    }
//
//    public void initialize() {
//
//        populatePatientList();
//        populateAppointmentList();
//
//        SelectionMode modePatients = patientsListView.getSelectionModel().getSelectionMode();
//        SelectionMode modeAppointments = appointmentsListView.getSelectionModel().getSelectionMode();
//    }
//
//    @FXML
//    void searchOnKeyTypedPatients(KeyEvent event) {
//        String searchText = patientSearchTextField.getText();
//        if (searchText.equals(""))
//            populatePatientList();
//        else {
//            ObservableList<Patient> filteredPatients = FXCollections.observableArrayList(dentalService.showPatientsFromAGivenCityOrderedById(searchText));
//            patientsListView.setItems(filteredPatients);
//        }
//    }
//
//    @FXML
//    void searchOnKeyTypedAppointments(KeyEvent event) {
//        String searchText = appointmentSearchTextField1.getText();
//        if (searchText.equals(""))
//            populateAppointmentList();
//        else {
//            ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList(dentalService.showAllAppointmentsOfAPatientByAGivenId(Integer.parseInt(searchText)));
//            appointmentsListView.setItems(filteredAppointments);
//        }
//    }
//
//    @FXML
//    void searchOnPatientNameTextField(KeyEvent event) {
//        String searchText = patientNameStartingTextField.getText();
//        char firstChar = searchText.charAt(0);
//        if (searchText.equals("C"))
//            populatePatientList();
//        else {
//            ObservableList<Patient> filteredPatients = FXCollections.observableArrayList();
//            patientsListView.setItems(filteredPatients);
//        }
//    }
//
//    @FXML
//    void  searchOnPhoneNumberIdTextField(KeyEvent event) {
//        String searchText = phoneNumberIdTextField.getText();
//        if (searchText.equals(""))
//            populatePatientList();
//        else {
//            foundPhoneNumber.setText(String.valueOf(dentalService.getPhoneNumberById(Integer.parseInt(String.valueOf(searchText)))));
//        }
//    }
//
//    @FXML
//    void onSearchDateBefore(KeyEvent event) {
//        String searchText = dateBefore.getText();
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(dateBefore.getText(), dateFormatter);
//        if (searchText.isEmpty())
//            populateAppointmentList();
//        else {
//            ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList(dentalService.filteringAppointmentsBeforeDate(date));
//            appointmentsListView.setItems(filteredAppointments);
//        }
//    }
//
//    @FXML
//    void onClickPatientsList(MouseEvent event) {
//        int idx = patientsListView.getSelectionModel().getSelectedIndex();
//        ArrayList<Patient> patients = dentalService.getAllPatientsFromThePatientsRepository();
//        if (idx < 0 || idx >= patients.size()) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Invalid patient selection!");
//            dialog.show();
//        } else {
//            Patient patient = patients.get(idx);
//            nameTextField.setText(patient.getName());
//            idTextField.setText(patient.getId().toString());
//            cityTextField.setText(patient.getCity());
//            phoneNumberTextField.setText(String.valueOf(patient.getPhoneNumber()));
//        }
//    }
//
//    @FXML
//    void onClickAppointmentsList(MouseEvent event) {
//        int idx = appointmentsListView.getSelectionModel().getSelectedIndex();
//        ArrayList<Appointment> appointments = dentalService.getAllAppointmentsFromTheAppointmentsRepository();
//        if(idx < 0 || idx >= appointments.size())
//        {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Invalid appointment selection!");
//            dialog.show();
//        }else {
//            Appointment appointment = appointments.get(idx);
//            appointmentIdTextField.setText(String.valueOf(appointment.getId()));
//            patientNameTextField.setText(appointment.getPatient().getName());
//            patientCityTextField.setText(appointment.getPatient().getCity());
//            patientPhoneNumberTextField.setText(String.valueOf(appointment.getPatient().getPhoneNumber()));
//            patientIdTextField.setText(String.valueOf(appointment.getPatient().getId()));
//            dateTextField.setText(String.valueOf(appointment.getDateOfAppointment()));
//
//        }
//    }
//
//    @FXML
//    void onPatientAddButtonClicked(MouseEvent event) {
//        try {
//            Integer id = Integer.parseInt(idTextField.getText());
//            Integer phoneNumber = Integer.parseInt(phoneNumberTextField.getText());
//            dentalService.addPatient(nameTextField.getText(), id, cityTextField.getText(),
//                    phoneNumber);
//
//            populatePatientList();
//        } catch (NumberFormatException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Id and phone must be numbers!");
//            dialog.show();
//        } catch (DuplicateItemException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Item already exists!");
//            dialog.show();
//        }
//    }
//
//    @FXML
//    void onAppointmentAddButtonClicked(MouseEvent event) {
//        try {
//            Integer id = Integer.parseInt(patientIdTextField.getText());
//            Integer phoneNumber = Integer.parseInt(patientPhoneNumberTextField.getText());
//            Integer appointmentNumber = Integer.parseInt(appointmentIdTextField.getText());
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate date = LocalDate.parse(dateTextField.getText(), dateFormatter);
//            Patient patientToAdd = new Patient(patientNameTextField.getText(), id, patientCityTextField.getText(), phoneNumber);
//            try {dentalService.addPatient(patientNameTextField.getText(), id, patientCityTextField.getText(), phoneNumber);
//                populatePatientList();
//                dentalService.addAppointment(appointmentNumber, patientToAdd, date);
//                populateAppointmentList();
//            } catch (DuplicateItemException e) {
//                Alert dialog = new Alert(Alert.AlertType.ERROR);
//                dialog.setTitle("Error");
//                dialog.setContentText("Item already exists!");
//                dialog.show();
//            }
//        } catch (NumberFormatException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Id must be a number!");
//            dialog.show();
//        } catch (DateTimeParseException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Invalid date format!");
//            dialog.show();
//        }
//    }
//
//
//    @FXML
//    void onPatientDeleteButtonClicked(MouseEvent event) {
//        int idx = patientsListView.getSelectionModel().getSelectedIndex();
//        if (idx >= 0) {
//            Patient patientToDelete = patientsListView.getItems().get(idx);
//            try {
//                // Delete patient
//                dentalService.deletePatientByID(patientToDelete.getId());
//
//                // Delete associated appointments
//                Collection<Appointment> appointmentsToRemove = new ArrayList<>();
//                for (Appointment appointment : dentalService.getAllAppointmentsFromTheAppointmentsRepository()) {
//                    if (appointment.getPatient().getId() == patientToDelete.getId()) {
//                        appointmentsToRemove.add(appointment);
//                    }
//                }
//                for (Appointment appointment : appointmentsToRemove) {
//                    dentalService.deleteAppointmentByID(appointment.getId());
//                }
//            } catch (ItemNotFound e) {
//                Alert dialog = new Alert(Alert.AlertType.ERROR);
//                dialog.setTitle("Error");
//                dialog.setContentText("No patient found!");
//                dialog.show();
//            }
//            populatePatientList();
//            populateAppointmentList();
//        } else {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("No patient selected!");
//            dialog.show();
//        }
//    }
//
//
//    @FXML
//    void onAppointmentDeleteButtonClicked(MouseEvent event) {
//        int idx = appointmentsListView.getSelectionModel().getSelectedIndex();
//        Appointment appointmentToDelete = appointmentsListView.getItems().get(idx);
//        try {
//            dentalService.deleteAppointmentByID(appointmentToDelete.getId());
//        } catch (ItemNotFound e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("No appointment found!");
//            dialog.show();
//        }
//        populateAppointmentList();
//    }
//
//    @FXML
//    void onPatientUpdateButtonClicked(MouseEvent event) {
//        try{
//            int idx = patientsListView.getSelectionModel().getSelectedIndex();
//            Patient patientToUpdate = patientsListView.getItems().get(idx);
//            Integer phoneNumber = Integer.parseInt(phoneNumberTextField.getText());
//            dentalService.updatePatient(nameTextField.getText(), patientToUpdate.getId(), cityTextField.getText(), phoneNumber);
//            Collection<Appointment> appointmentsToUpdate = dentalService.showAllAppointmentsOfAPatientByAGivenId(patientToUpdate.getId());
//            for (Appointment appointment : appointmentsToUpdate) {
//                appointment.setPatient(dentalService.getPatientByID(patientToUpdate.getId()));
//            }
//        } catch (ItemNotFound e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("No patient found!");
//            dialog.show();
//        } catch (NumberFormatException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Phone number must be a number!");
//            dialog.show();
//        }
//
//        populatePatientList();
//        populateAppointmentList();
//    }
//
//
//
//    @FXML
//    void onAppointmentUpdateButtonClicked(MouseEvent event) {
//        try {
//            int idx = appointmentsListView.getSelectionModel().getSelectedIndex();
//            if (idx >= 0) {
//                Appointment appointmentToUpdate = appointmentsListView.getItems().get(idx);
//                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate date = LocalDate.parse(dateTextField.getText(), dateFormatter);
//                Integer phoneNumber = Integer.parseInt(patientPhoneNumberTextField.getText());
//                Patient patientToUpdate = new Patient(patientNameTextField.getText(), appointmentToUpdate.getPatient().getId(), patientCityTextField.getText(), phoneNumber);
//
//                dentalService.updatePatient(patientNameTextField.getText(), appointmentToUpdate.getPatient().getId(), patientCityTextField.getText(), phoneNumber);
//                dentalService.updateAppointment(appointmentToUpdate.getId(), date);
//                Collection<Appointment> appointmentsToUpdate = dentalService.showAllAppointmentsOfAPatientByAGivenId(patientToUpdate.getId());
//                for (Appointment appointment : appointmentsToUpdate) {
//                    appointment.setPatient(dentalService.getPatientByID(patientToUpdate.getId()));
//                }
//            } else {
//                Alert dialog = new Alert(Alert.AlertType.ERROR);
//                dialog.setTitle("Error");
//                dialog.setContentText("No appointment selected!");
//                dialog.show();
//            }
//        } catch (ItemNotFound e) {
//            throw new RuntimeException(e);
//        } catch (DateTimeParseException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Invalid date format!");
//            dialog.show();
//        } catch (NumberFormatException e) {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Error");
//            dialog.setContentText("Phone number must be a number!");
//            dialog.show();
//        }
//        populatePatientList();
//        populateAppointmentList();
//    }
//
//    @FXML
//    void onBinaryButtonClicked(MouseEvent event) {
//        setRepositoryType(DentalService.RepositoryType.BINARY);
//    }
//
//    @FXML
//    void onFileButtonClicked(MouseEvent event) {
//        setRepositoryType(DentalService.RepositoryType.TEXT);
//    }
//
//    @FXML
//    void onDatabaseButtonClicked(MouseEvent event) {
//        setRepositoryType(DentalService.RepositoryType.DATABASE);
//    }
//
//    @FXML
//    void onInMemoryButtonClicked(MouseEvent event) {
//        setRepositoryType(DentalService.RepositoryType.MEMORY);
//    }
//
//    private void setRepositoryType(DentalService.RepositoryType repositoryType)
//    {
//        dentalService.setRepositoryType(repositoryType);
//        populatePatientList();
//        populateAppointmentList();
//        currentRepositoyLabel.setText(repositoryType.toString());
//    }
//
//
//
//}
//
////import Domain.Patient;
////import Domain.ValidationException;
////import javafx.collections.FXCollections;
////import javafx.collections.ObservableList;
////import javafx.fxml.FXML;
////import javafx.scene.control.Button;
////import javafx.scene.control.Label;
////import Service.*;
////import javafx.scene.control.ListView;
////import javafx.scene.control.TextField;
////
////import java.awt.event.ActionEvent;
////
////public class HelloController {
////
////    public Service service;
////
////    public ReportService reportService;
////    @FXML
////    private TextField searchTextFile;
////
////    @FXML
////    private ListView<Patient> patientsListView;
////
////    public HelloController(Service service) {
////        this.service=service;
////
////    }
////    void populateList() {
////        Iterable<Patient> orderIterable = service.getAllPatients();
////        // Convert the iterable to an ObservableList
////        ObservableList<Patient> patientsList = FXCollections.observableArrayList();
////        orderIterable.forEach(patientsList::add);
////
////        // Set the items in the ListView
////        patientsListView.setItems(patientsList);
////    }
////
////    public void initialize(){
////        populateList();
////    }
////
////    @FXML
////    private TextField idPatientTextFiled;
////
////    @FXML
////    private TextField namePatientTextFiled;
////
////    @FXML
////    private TextField diseasePatientTextFiled;
////
////    @FXML
////    private Button addPatientButton;
////
////    @FXML
////    private Button removePatientButton;
////
////    @FXML
////    void addOrder(ActionEvent event) throws ValidationException {
////        int id =Integer.parseInt(idPatientTextFiled.getText());
////        String name =namePatientTextFiled.getText();
////        String disease=diseasePatientTextFiled.getText();
////        Patient p = new Patient(id, name, disease);
////        service.addPatient(p);
////        populateList();
////
////    }
////
////    @FXML
////    void removeOrder(ActionEvent event) throws ValidationException {
////        int id =Integer.parseInt(idPatientTextFiled.getText());
////        service.deletePatient(id);
////        populateList();
////    }
////    @FXML
////    void updateOrder(ActionEvent event) throws ValidationException {
////        int id =Integer.parseInt(idPatientTextFiled.getText());
////        String name =namePatientTextFiled.getText();
////        String disease=diseasePatientTextFiled.getText();
////        Patient p1 = new Patient(id, name, disease);
////        service.updatePatient(p1);
////        populateList();
////
////
////    }
////    @FXML
////    private TextField text1;
////
////    @FXML
////    private TextField text2;
////
////    @FXML
////    private TextField text3;
////
////    @FXML
////    void getPatientsWithDisease(ActionEvent event) {
////        String disease =text3.getText();
////        Iterable<Patient> orderIterable = reportService.getPatientsWithDisease(disease);
////
////        // Convert the iterable to an ObservableList
////        ObservableList<Patient> ordersList = FXCollections.observableArrayList();
////        orderIterable.forEach(ordersList::add);
////
////        // Set the items in the ListView
////        patientsListView.setItems(ordersList);
////    }
////
////    @FXML
////    void getPatientsWithInitial(ActionEvent event) {
////        String initial =text3.getText();
////        char firstChar = initial.charAt(0);
////        Iterable<Patient> orderIterable = reportService.getPatientsWithInitial(firstChar);
////
////        // Convert the iterable to an ObservableList
////        ObservableList<Patient> ordersList = FXCollections.observableArrayList();
////        orderIterable.forEach(ordersList::add);
////
////        // Set the items in the ListView
////        patientsListView.setItems(ordersList);
////
////    }
////
////
////
////
////
////
////
////
////
//////        @FXML
//////    private Label welcomeText;
//////
//////    @FXML
//////    protected void onHelloButtonClick() {
//////        welcomeText.setText("Welcome to JavaFX Application!");
//////    }
////}