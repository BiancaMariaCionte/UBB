package gui;

import domain.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Route> routesListView;

    private ObservableList<Route> originalRoutesList;


    void populateList()
    {
        List<Route> routeList = service.getAllRouts();
        originalRoutesList = FXCollections.observableArrayList(routeList);

        ObservableList<String> cities = FXCollections.observableArrayList();
        for (Route route : routeList) {
            String city = route.getSourceCity();
            if (!cities.contains(city)) {
                cities.add(city);
            }
        }
        sourceCityComboBox.setItems(cities);
        routesListView.setItems(originalRoutesList);
    }



    public void initialize()
    {
        if(totalPriceText.getText().equals("Text")) {
            totalPriceText.setText("0");
        }
        populateList();
    }

    @FXML
    private ComboBox<String > sourceCityComboBox;

    @FXML
    void sourceCityComboBoxOnAction(ActionEvent event) {
        String selectedCity = sourceCityComboBox.getValue();
        if (selectedCity != null) {
            List<Route> filteredRoutes = originalRoutesList.stream()
                    .filter(route -> route.getSourceCity().equals(selectedCity))
                    .collect(Collectors.toList());

            // Extract destination cities from the filtered routes
            ObservableList<String> destinationCities = FXCollections.observableArrayList();
            for (Route route : filteredRoutes) {
                String destinationCity = route.getDestinationCity();
                if (!destinationCities.contains(destinationCity)) {
                    destinationCities.add(destinationCity);
                }
            }

            // Update the destination city combo box
            destinationCityComboBox.setItems(destinationCities);
        }
    }
    @FXML
    private ComboBox<String> destinationCityComboBox;

    @FXML
    void destinationCityComboBoxOnAction(ActionEvent event) {
        String sourceCity = sourceCityComboBox.getValue();
        String destinationCity = destinationCityComboBox.getValue();
        ObservableList<Route> filteredList = FXCollections.observableArrayList(service.showAvailableRoutes(sourceCity,destinationCity));
        routesListView.setItems(filteredList);

    }

    @FXML
    private TextField noOfTickestTF;


    @FXML
    private Button bookButton;

    @FXML
    private Text totalPriceText;

    @FXML
    void bookButtonClicked(MouseEvent event) {
        Route route = routesListView.getSelectionModel().getSelectedItem();
        Integer noOfTickets = Integer.valueOf(noOfTickestTF.getText());

        // Check if the number of tickets chosen exceeds the available seats
        if (noOfTickets > route.getNoOfSeats()) {
            // Display the error window for insufficient seats
            try {
                //Create Stage
                Stage newWindow = new Stage();
                newWindow.setTitle("New Scene");
                //Create view from FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorWindow.fxml"));
                //Set view in window
                newWindow.setScene(new Scene(loader.load()));
                newWindow.setTitle("Error!");
                //Launch
                newWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Proceed with computing the price
            service.updateAvailableTickets(route, noOfTickets);
            totalPriceText.setText(String.valueOf(route.getPrice() * noOfTickets));
            ObservableList<Route> routes = FXCollections.observableArrayList(service.getAll());
            routesListView.setItems(routes);
        }
    }

}
