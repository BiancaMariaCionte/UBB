package gui;

import domain.Fitness;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.Service;

import java.util.Collections;
import java.util.List;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Fitness> fitnessListView;


    void populateList()
    {

        ObservableList<Fitness> questionsList = FXCollections.observableArrayList(service.getAllFitness());
        fitnessListView.setItems(questionsList);

    }

    public void initialize()
    {
        if(textText.getText().equals("Text")) {
            textText.setText("0");
        }
        populateList();
    }

    @FXML
    private TextField moveMinutesTF;

    @FXML
    private Button searchButton;

    @FXML
    void searchButtonClicked(MouseEvent event) {
        Integer text = Integer.valueOf(moveMinutesTF.getText());
        ObservableList<Fitness> filteredList = FXCollections.observableArrayList();
        filteredList.addAll(service.searchFitnessBy(text));
        fitnessListView.setItems(filteredList);

    }
    @FXML
    private TextField activitiesTF;

    @FXML
    private Button addButton;

    @FXML
    private TextField dateTF;
    @FXML
    private TextField hoursOfSleepTF;

    @FXML
    private TextField monthTF;
    @FXML
    private TextField noOfSteptsTF;

    @FXML
    void addButtonClicked(MouseEvent event) {
        String date = dateTF.getText();
        Integer steps = Integer.parseInt(noOfSteptsTF.getText());
        Double sleep = Double.parseDouble(hoursOfSleepTF.getText());
        List<String> activities = Collections.singletonList(activitiesTF.getText());
        Fitness fit = new Fitness(date,steps,sleep,activities);

        service.add(fit);
        ObservableList<Fitness> fitnessObservableList = FXCollections.observableArrayList(service.getAll());
        fitnessListView.setItems(fitnessObservableList);

    }


    @FXML
    private Button noOfStepsButton;

    @FXML
    void noOfStepsButtonClicked(MouseEvent event) {
        String month = monthTF.getText();
        textText.setText(String.valueOf(service.noOfStepsByMonth(month)));

    }

    @FXML
    private Text textText;

}
