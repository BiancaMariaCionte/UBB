package gui;

import domain.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    private ObservableList<Weather> originalWeatherList;
    @FXML
    private Button populateListButton;

    @FXML
    private ListView<Weather> weatherListView;

    @FXML
    private ComboBox<String> descriptionComboBox;


    // We POPULATE the COMBO BOX
    void populateList()
    {

        List<Weather> weatherList = service.getAll();
        originalWeatherList = FXCollections.observableArrayList(weatherList);

        ObservableList<String> descriptions = FXCollections.observableArrayList();
        for (Weather weather : weatherList) {
            String firstWord = weather.getDescription().split(",")[0].trim();
            if (!descriptions.contains(firstWord)) {
                descriptions.add(firstWord);
            }
        }
        descriptionComboBox.setItems(descriptions);
        weatherListView.setItems(originalWeatherList);

    }

    @FXML
    private Text noOfHoursText;
    public void initialize()
    {
        if(noOfHoursText.getText().equals("Text")) {
            noOfHoursText.setText("0");
        }
        populateList();
    }

    // We UPDATE our list based on the selected value form the combo box

    @FXML
    void descriptionComboBoxOnAction(ActionEvent event) {
        String selectedDescription = descriptionComboBox.getValue();
        if (selectedDescription != null) {
            List<Weather> filteredList = originalWeatherList.stream()
                    .filter(weather -> weather.getDescription().startsWith(selectedDescription))
                    .sorted(Comparator.comparing(Weather::getStartHour))
                    .collect(Collectors.toList());
            weatherListView.setItems(FXCollections.observableArrayList(filteredList));
        }
    }

    @FXML
    void populateListButtonClicked(MouseEvent event) {

        weatherListView.setItems(originalWeatherList);
    }

    @FXML
    private Button updateButton;

    @FXML
    private TextField precipitationTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    void updateButtonClicked(MouseEvent event) {
        Weather selectedWeather = weatherListView.getSelectionModel().getSelectedItem();
        if(selectedWeather!=null)
        {
            Integer newPrecipitationProbability = Integer.parseInt(precipitationTF.getText());
            String newDescription = descriptionTF.getText();

            service.updateWeather(selectedWeather,newPrecipitationProbability,newDescription);

        }

    }

    @FXML
    private Button searchButton;

    @FXML
    private TextField inputWordTF;

    @FXML
    void searchButtonClicked(MouseEvent event) {
        String inputWord = inputWordTF.getText();
        ObservableList<Weather> filteredList = FXCollections.observableArrayList(service.searchWeather(inputWord));
        weatherListView.setItems(filteredList);
        int sum = 0;
        int interval = 0;

        for(Weather weather :filteredList)
        {

            interval = weather.getEndHour() - weather.getStartHour();
            sum = sum + interval;
        }

        noOfHoursText.setText(String.valueOf(sum));

    }


}
