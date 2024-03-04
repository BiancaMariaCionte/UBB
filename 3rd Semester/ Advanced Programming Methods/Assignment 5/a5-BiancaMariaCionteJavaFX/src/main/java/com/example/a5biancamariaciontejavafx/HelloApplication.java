package com.example.a5biancamariaciontejavafx;

import Domain.IAction;
import Domain.Patient;
import Domain.Appointment;
import Domain.ValidationException;
import Repository.*;
import Service.ReportService;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class HelloApplication extends Application {
    ListView<Patient> patientsListView = new ListView<>();
    Button addButton = new Button("Add");
    Button removeButton = new Button("Remove");
    Button updateButton = new Button("Update");

    Button undoButton = new Button("Undo");
    Button redoButton = new Button("Redo");
    Button undoAppointmentButton = new Button("Undo Appointment");
    Button redoAppointmentButton = new Button("Redo Appointment");

    Button inMemoryButton = new Button("In Memory Repository");
    Button fileRepositoryButton = new Button("File Repository");
    Button binaryFileRepositoryButton = new Button("Binary File Repository");
    Button dbRepositoryButton = new Button("Database Repository");
    Button showReportsButton = new Button("Show Reports");


    TextField idTextField = new TextField();
    TextField nameTextField = new TextField();
    TextField diseaseTextField = new TextField();

    // Define undo and redo stacks
    private Stack<IAction<Integer, Patient>> undoStack = new Stack<>();
    private Stack<IAction<Integer, Patient>> redoStack = new Stack<>();

    private Stack<IAction<Integer, Appointment>> undoAppointmentStack = new Stack<>();
    private Stack<IAction<Integer, Appointment>> redoAppointmentStack = new Stack<>();


    ObservableList<Patient> patientList;

    GenericRepository<Patient, Integer> patientRepository;

    // New controls for handling appointments
    ListView<Appointment> appointmentsListView = new ListView<>();
    Button addAppointmentButton = new Button("Add Appointment");
    Button removeAppointmentButton = new Button("Remove Appointment");
    Button updateAppointmentButton = new Button("Update Appointment");

    TextField appointmentIdTextField = new TextField();
    TextField descriptionTextField = new TextField();
    TextField dateTextField = new TextField();

    ObservableList<Appointment> appointmentList;
    GenericRepository<Appointment, Integer> appointmentRepository;

    //ObservableList represents a dynamic list that can be observed for changes.
    // In this case, it's used to store the list of Patient objects.

    public Scene createScene() {
        HBox hbox = new HBox();
        Scene scene = new Scene(hbox, 900, 700); // Increased height to accommodate both sections
        hbox.getChildren().addAll(createPatientSection(), createAppointmentSection());
        return scene;
    }

    private VBox createPatientSection() {
        GridPane patientDataPane = new GridPane();
        patientDataPane.setHgap(10);
        patientDataPane.setVgap(5);

        // Use ColumnConstraints to set vertical alignment
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);  // Align labels to the right
        patientDataPane.getColumnConstraints().add(column1);

        ////

        showReportsButton.setOnMouseClicked(event -> showReports());
        //

        patientDataPane.add(new Label("Id"), 0, 0);
        patientDataPane.add(idTextField, 1, 0);
        patientDataPane.add(new Label("Name"), 0, 1);
        patientDataPane.add(nameTextField, 1, 1);
        patientDataPane.add(new Label("Disease"), 0, 2);
        patientDataPane.add(diseaseTextField, 1, 2);
        patientDataPane.add(addButton, 0, 3);
        patientDataPane.add(removeButton, 1, 3);
        patientDataPane.add(updateButton, 0, 4);  // Place the updateButton in a new row
        patientDataPane.add(undoButton, 1, 4);
        patientDataPane.add(redoButton, 0, 5, 2, 1);  // Span two columns for redoButton

        VBox repositoryButtons = new VBox();
        repositoryButtons.setSpacing(10);
        repositoryButtons.getChildren().addAll(inMemoryButton, fileRepositoryButton, binaryFileRepositoryButton, dbRepositoryButton, showReportsButton);
///////////////
        return new VBox(patientsListView, patientDataPane, repositoryButtons);
    }

    private VBox createAppointmentSection() {
        GridPane appointmentDataPane = new GridPane();
        appointmentDataPane.setHgap(10);
        appointmentDataPane.setVgap(5);
        appointmentDataPane.add(new Label("Id"), 0, 0);
        appointmentDataPane.add(appointmentIdTextField, 1, 0);
        appointmentDataPane.add(new Label("Description"), 0, 1);
        appointmentDataPane.add(descriptionTextField, 1, 1);
        appointmentDataPane.add(new Label("Date"), 0, 2);
        appointmentDataPane.add(dateTextField, 1, 2);
        appointmentDataPane.add(addAppointmentButton, 0, 4);
        appointmentDataPane.add(removeAppointmentButton, 1, 4);
        appointmentDataPane.add(updateAppointmentButton, 2, 4);
        appointmentDataPane.add(undoAppointmentButton, 3, 4);
        appointmentDataPane.add(redoAppointmentButton, 4, 4);

        /////////

        showReportsButton.setOnMouseClicked(event -> showReports());
        /////////////////
        VBox repositoryButtons = new VBox();
        repositoryButtons.setSpacing(10);
        repositoryButtons.getChildren().addAll(inMemoryButton, fileRepositoryButton, binaryFileRepositoryButton, dbRepositoryButton, showReportsButton);

        return new VBox(appointmentsListView, appointmentDataPane, repositoryButtons);
    }

    private void showReports() {
        ReportService reportService = new ReportService(patientRepository, appointmentRepository);
        List<String> reports = reportService.getReports(); // Assuming getReports() returns a list of report strings

        // Create a new stage for displaying reports
        Stage reportStage = new Stage();
        reportStage.setTitle("Reports");

        // Create a VBox to hold the report information
        VBox reportBox = new VBox();
        reportBox.setSpacing(10);

        // Add labels or text areas for each report
        for (String report : reports) {
            Label reportLabel = new Label(report);
            reportBox.getChildren().add(reportLabel);
        }

        // Create a scene with the report VBox
        Scene reportScene = new Scene(reportBox, 400, 300);

        // Set the scene for the stage
        reportStage.setScene(reportScene);

        // Show the report stage
        reportStage.show();
        //reportService.displayReports();
    }

    public void handleEvents() {
        // Repository type selection buttons
        inMemoryButton.setOnMouseClicked(event -> initializeRepository(new MemoryRepository<>(), new MemoryRepository<>()));
        fileRepositoryButton.setOnMouseClicked(event -> initializeRepository(new PatientFileRepository("C://Users//Bianca//IdeaProjects//a5-BiancaMariaCionteJavaFX//src//Patients_10.txt"), new AppointmentFileRepository("C://Users//Bianca//IdeaProjects//a5-BiancaMariaCionteJavaFX//src//Appointments_10.txt")));
        binaryFileRepositoryButton.setOnMouseClicked(event -> initializeRepository(new PatientBinaryFileRepository("C://Users//Bianca//IdeaProjects//a5-BiancaMariaCionteJavaFX//src//Patients_10.bin"), new AppointmentBinaryFileRepository("C://Users//Bianca//IdeaProjects//a5-BiancaMariaCionteJavaFX//src//Appointments_10.bin")));
        dbRepositoryButton.setOnMouseClicked(event -> initializeRepository(new PatientDBRepository("patients"), new AppointmentDBRepository("appointments")));

        addButton.setOnMouseClicked(event -> {
            try {
                String id = idTextField.getText();
                String name = nameTextField.getText();
                String disease = diseaseTextField.getText();
                Patient patient = new Patient(Integer.parseInt(id), name, disease);


                // Create action and execute it
                ActionAdd<Integer, Patient> addAction = new ActionAdd<>(patientRepository, patient);
                addAction.executeRedo();

                // Push the action to undo stack
                undoStack.push(addAction);

                // Clear the redo stack
                redoStack.clear();


                //patientRepository.addEntity(patient);

                refreshListView();
            } catch (ValidationException e) {
                showAlert("Error", "Validation Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        patientsListView.setOnMouseClicked(event -> {
            int idx = patientsListView.getSelectionModel().getSelectedIndex();
            if (idx < 0)
                return;
            Patient patient = patientList.get(idx);
            idTextField.setText(String.valueOf(patient.getId()));
            nameTextField.setText(patient.getName());
            diseaseTextField.setText(String.valueOf(patient.getDisease()));
        });

        removeButton.setOnMouseClicked(event -> {
            try {
                int idx = patientsListView.getSelectionModel().getSelectedIndex();
                Patient patient = patientList.get(idx);

                // Create action and execute it
                ActionRemove<Integer, Patient> removeAction = new ActionRemove<>(patientRepository, patient);
                removeAction.executeRedo();

                // Push the action to undo stack
                undoStack.push(removeAction);

                // Clear the redo stack
                redoStack.clear();

                //patientRepository.deleteEntity(patient.getId());

                refreshListView();
            } catch (ValidationException e) {
                showAlert("Error", "Validation Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        updateButton.setOnMouseClicked(event -> {
            try {
                int idx = patientsListView.getSelectionModel().getSelectedIndex();
                Patient patient = patientList.get(idx);
                String name = nameTextField.getText();
                String disease = diseaseTextField.getText();
                Patient updatedPatient = new Patient(patient.getId(), name, disease);

                // Create action and execute it
                ActionUpdate<Integer, Patient> updateAction = new ActionUpdate<>(patientRepository, patient, updatedPatient);
                updateAction.executeRedo();

                // Push the action to undo stack
                undoStack.push(updateAction);

                // Clear the redo stack
                redoStack.clear();


                //patientRepository.updateEntity(patient.getId(), updatedPatient);

                refreshListView();
            } catch (ValidationException e) {
                showAlert("Error", "Validation Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        // Handle undo and redo buttons
        undoButton.setOnMouseClicked(event -> {
            if (!undoStack.isEmpty())
            {
                IAction<Integer, Patient> action = undoStack.pop();
                try {
                    action.executeUndo();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                redoStack.push(action);
                refreshListView();
            }
        });
        // pops an action from the undo stack,
        // executes its undo operation, and pushes it onto the redo stack.

        redoButton.setOnMouseClicked(event -> {
            if (!redoStack.isEmpty()) {
                IAction<Integer, Patient> action = redoStack.pop();
                try {
                    action.executeRedo();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                undoStack.push(action);
                refreshListView();
            }
        });
        // pops an action from the redo stack,
        // executes its redo operation, and pushes it onto the undo stack.



        // Handle appointment events
        addAppointmentButton.setOnMouseClicked(event -> {
            try {
                String appointmentId = appointmentIdTextField.getText();
                String description = descriptionTextField.getText();
                String date = dateTextField.getText();
                Appointment appointment = new Appointment(Integer.parseInt(appointmentId), description, date);

                // Create action and execute it
                ActionAdd<Integer, Appointment> addAction = new ActionAdd<>(appointmentRepository, appointment);
                addAction.executeRedo();

                // Push the action to undo stack
                undoAppointmentStack.push(addAction);

                // Clear the redo stack
                redoAppointmentStack.clear();


                //appointmentRepository.addEntity(appointment);

                refreshAppointmentListView();
            } catch (ValidationException e) {
                showAlert("Error", "Validation Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        appointmentsListView.setOnMouseClicked(event -> {
            int idx = appointmentsListView.getSelectionModel().getSelectedIndex();
            if (idx < 0)
                return;
            Appointment appointment = appointmentList.get(idx);
            appointmentIdTextField.setText(String.valueOf(appointment.getId()));
            descriptionTextField.setText(appointment.getDescription());
            dateTextField.setText(appointment.getDate());
        });

        removeAppointmentButton.setOnMouseClicked(event -> {
            try {
                int idx = appointmentsListView.getSelectionModel().getSelectedIndex();
                Appointment appointment = appointmentList.get(idx);

                // Create action and execute it
                ActionRemove<Integer, Appointment> removeAction = new ActionRemove<>(appointmentRepository, appointment);
                removeAction.executeRedo();

                // Push the action to undo stack
                undoAppointmentStack.push(removeAction);

                // Clear the redo stack
                redoAppointmentStack.clear();


                //appointmentRepository.deleteEntity(appointment.getId());

                refreshAppointmentListView();
            } catch (ValidationException e) {
                showAlert("Error", "Validation Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        updateAppointmentButton.setOnMouseClicked(event -> {
            try {
                int idx = appointmentsListView.getSelectionModel().getSelectedIndex();
                Appointment appointment = appointmentList.get(idx);
                String description = descriptionTextField.getText();
                String date = dateTextField.getText();
                Appointment updatedAppointment = new Appointment(appointment.getId(), description, date);

                // Create action and execute it
                ActionUpdate<Integer, Appointment> updateAction = new ActionUpdate<>(appointmentRepository, appointment, updatedAppointment);
                updateAction.executeRedo();

                // Push the action to undo stack
                undoAppointmentStack.push(updateAction);

                // Clear the redo stack
                redoAppointmentStack.clear();


                //appointmentRepository.updateEntity(appointment.getId(), updatedAppointment);

                refreshAppointmentListView();
            } catch (ValidationException e) {
                showAlert("Error", "Validation Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });
        undoAppointmentButton.setOnMouseClicked(event -> {
            if (!undoAppointmentStack.isEmpty()) {
                IAction<Integer, Appointment> action = undoAppointmentStack.pop();
                try {
                    action.executeUndo();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                redoAppointmentStack.push(action);
                refreshAppointmentListView();
            }
        });

        redoAppointmentButton.setOnMouseClicked(event -> {
            if (!redoAppointmentStack.isEmpty()) {
                IAction<Integer, Appointment> action = redoAppointmentStack.pop();
                try {
                    action.executeRedo();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                undoAppointmentStack.push(action);
                refreshAppointmentListView();
            }
        });

    }


    private void initializeRepository(GenericRepository<Patient, Integer> patientRepository, GenericRepository<Appointment, Integer> appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        refreshListView();
        refreshAppointmentListView(); // Initialize appointment list view
    }

    private void refreshAppointmentListView() {
        Object itemsObject = appointmentRepository.getAll();

        if (itemsObject instanceof Collection<?>) {
            Collection<?> items = (Collection<?>) itemsObject;
            appointmentsListView.getItems().clear();

            for (Object item : items) {
                if (item instanceof Appointment) {
                    appointmentsListView.getItems().add((Appointment) item);
                } else {
                    System.err.println("Unexpected item type in appointment repository: " + item.getClass());
                }
            }
        } else if (itemsObject instanceof java.util.Map<?, ?>) {
            java.util.Map<?, ?> itemsMap = (java.util.Map<?, ?>) itemsObject;
            appointmentsListView.getItems().clear();

            for (Object item : itemsMap.values()) {
                if (item instanceof Appointment) {
                    appointmentsListView.getItems().add((Appointment) item);
                } else {
                    System.err.println("Unexpected item type in appointment repository: " + item.getClass());
                }
            }
        } else {
            System.err.println("Unexpected return type from appointment repository: " + itemsObject.getClass());
        }
    }






    private void refreshListView() {
        Object itemsObject = patientRepository.getAll();
        //Collections allow us to organize and group related elements together
        //Collections can dynamically grow or shrink in size
        //Compatibility with Various Repository Implementations

        if (itemsObject instanceof Collection<?>) {
            // Handle the case when the repository returns a collection of items
            Collection<?> items = (Collection<?>) itemsObject;
            patientsListView.getItems().clear();

            for (Object item : items) {
                if (item instanceof Patient) {
                    patientsListView.getItems().add((Patient) item);
                } else if (item instanceof Appointment) {
                    // Handle Appointment type
                }
            }
        } else if (itemsObject instanceof java.util.Map<?, ?>) {
            // Handle the case when the repository returns a map of items
            java.util.Map<?, ?> itemsMap = (java.util.Map<?, ?>) itemsObject;
            patientsListView.getItems().clear();

            for (Object item : itemsMap.values()) {
                if (item instanceof Patient) {
                    patientsListView.getItems().add((Patient) item);
                } else if (item instanceof Appointment) {
                    // Handle Appointment type
                }

            }
        } else {
            // Handle the case when the repository returns a different type
            System.err.println("Unexpected return type from repository: " + itemsObject.getClass());
        }
    }

    private void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void populateList() {
        // Populate the initial data (you can remove this in a real application)
        Patient s1 = new Patient(1, "Mihai", "flu");
        Patient s2 = new Patient(2, "Ana", "pneumonia");
        Patient s3 = new Patient(3, "Ionut", "insomnia");

        patientList = FXCollections.observableArrayList(Arrays.asList(s1, s2, s3));
        patientsListView.setItems(patientList);

        // Initialize appointmentList
        Appointment a1 = new Appointment(1, "Checkup", "2023-12-01");
        Appointment a2 = new Appointment(2, "Follow-up", "2023-12-15");
        appointmentList = FXCollections.observableArrayList(Arrays.asList(a1, a2));
        appointmentsListView.setItems(appointmentList);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = createScene();
        populateList();

        // Default repository selection (you can change this based on your preference)
        initializeRepository(new MemoryRepository<>(), new MemoryRepository<>());

        stage.setTitle("Patient Management App");
        stage.setScene(scene);
        handleEvents();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}