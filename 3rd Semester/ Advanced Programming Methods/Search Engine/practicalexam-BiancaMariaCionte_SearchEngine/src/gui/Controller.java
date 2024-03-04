package gui;

import domain.SearchEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Service;

import java.util.Collections;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<SearchEngine> engineListView;

    void populateList()
    {

        ObservableList<SearchEngine> questionsList = FXCollections.observableArrayList(service.getAllQuestions());
        engineListView.setItems(questionsList);

    }

    public void initialize()
    {
//        if(totalScoreText.getText().equals("Text")) {
//            totalScoreText.setText("0");
//        }
        populateList();
    }

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTF;

    @FXML
    void searchButtonClicked(MouseEvent event) {
        String text = searchTF.getText().toLowerCase();
        ObservableList<SearchEngine> filteredList = FXCollections.observableArrayList();

        if(!text.isEmpty())
        {
            filteredList.addAll(service.searchDocumentBy(text));
        }else
            filteredList.addAll(service.getAllQuestions());

        engineListView.setItems(filteredList);

    }

    @FXML
    private TextField keywordsTF;

    @FXML
    private TextField contentTF;
    @FXML
    private Button updateButton;
    @FXML
    void updateButtonClicked(MouseEvent event) {
        SearchEngine document = engineListView.getSelectionModel().getSelectedItem();
        String newKeywords = keywordsTF.getText();
        String newContent = contentTF.getText();
        service.updateDocument(document, Collections.singletonList(newKeywords),newContent);

    }

    @FXML
    private Button bestMatchingButton;

    @FXML
    private TextField bestMatchingTF;

    @FXML
    void bestMatchingButtonClicked(MouseEvent event) {
        String bestMatchingText = bestMatchingTF.getText().toLowerCase();
        SearchEngine bestMatch = service.findBestMatchingDocument(bestMatchingText);

        if (bestMatch != null) {
            ObservableList<String> docslist = FXCollections.observableArrayList();
            String doc = "Name: " + bestMatch.getName() + " // Keywords: " + String.join(",", bestMatch.getKeywords())
                    + " // Content: " + bestMatch.getContent() + " // Similarity: " + service.computeSimilarity(bestMatchingText, bestMatch.getName());
            docslist.add(doc);
            listViewShort.setItems(docslist);
        } else {
            listViewShort.setItems(FXCollections.observableArrayList()); // Clear the listView
        }
    }

    @FXML
    private ListView<String> listViewShort;


}
