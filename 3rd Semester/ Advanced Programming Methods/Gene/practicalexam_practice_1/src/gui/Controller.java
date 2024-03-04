package gui;

import domain.Genes;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.Service;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller
{
    private Service service;

    public Controller(Service service)
    {
        this.service = service;
    }

    @FXML
    private ListView<Genes> geneListView;

    @FXML
    private ComboBox<String> organismComboBox;

    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize()
    {
        // Retrieve genes from the service
        FXCollections FXCollections;
        ObservableList<Genes> genes = javafx.collections.FXCollections.observableArrayList(service.getAll());

        // Sort genes by organism
        //genes.sort((gene1, gene2) -> gene1.getOrganism().compareTo(gene2.getOrganism()));
        genes.sort(Comparator.comparing(Genes::getOrganism));

        // Populate the ListView
        geneListView.setItems(genes);

        // Extract all unique organisms for the combo box
        ObservableList<String> organisms = genes.stream()
                .map(Genes::getOrganism)
                .distinct()
                .sorted()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        javafx.collections.FXCollections::observableArrayList
                ));

        // Populate the combo box with organisms
        organismComboBox.setItems(organisms);

        // Add listener for search
        organismComboBox.valueProperty().addListener((obs, oldValue, newValue) -> searchGenes());

        searchTextField.textProperty().addListener((obs, oldValue, newValue) -> searchGenes());
    }



    private void searchGenes()
    {
        // Declare ObservableList variable to store all genes
        ObservableList<Genes> allGenes; // Declare allGenes variable
        // Retrieve genes from the service and store them in allGenes
        allGenes = javafx.collections.FXCollections.observableArrayList(service.getAll());

        // Retrieve the selected organism from the organismComboBox
        String selectedOrganism = organismComboBox.getValue();
        // Retrieve the text entered, in the searchTextField and convert it to lowercase for case-insensitive search
        String searchText = searchTextField.getText().toLowerCase();

        // Filter the genes based on selected organism and search text
        ObservableList<Genes> filteredGenes = allGenes.stream()
                // Filter genes based on selected organism, if any
                .filter(gene -> selectedOrganism == null || gene.getOrganism().equals(selectedOrganism))
                // Filter genes based on search text (searching in gene name and function), ignoring case
                .filter(gene -> gene.getName().toLowerCase().contains(searchText) || gene.getFunction().toLowerCase().contains(searchText))
                // Collect the filtered genes into a new ObservableList
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Set the items of the geneListView to the filtered genes
        geneListView.setItems(filteredGenes);
    }


    @FXML
    private Button updateButton;
    @FXML
    private TextField functionTF;
    @FXML
    private TextArea sequenceAF;

    @FXML
    void listViewClicked(MouseEvent event) {
        Genes gene = geneListView.getSelectionModel().getSelectedItem();
        functionTF.setText(gene.getFunction());
        sequenceAF.setText(gene.getAssociated());

    }
    @FXML
    void updateButtonClicked(MouseEvent event) {
        Genes gene = geneListView.getSelectionModel().getSelectedItem();
        String newFunction = functionTF.getText();
        String newSequence = sequenceAF.getText();

        service.update(gene,newFunction,newSequence);
    }

    @FXML
    void showMutationButtonClicked(MouseEvent event) {
        // Get the selected gene from the list view
        Genes selectedGene = geneListView.getSelectionModel().getSelectedItem();

        // Get the sequences
        String selectedGeneSequence = selectedGene.getAssociated();
        String inputSequence = givenSeqTF.getText();

        // Find point mutations
        List<String> mutations = findPointMutations(selectedGeneSequence, inputSequence);

        // Display the mutations
        if (mutations.isEmpty()) {
            mutationTA.setText("No mutations found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String mutation : mutations) {
                sb.append(mutation).append("\n");
            }
            mutationTA.setText(sb.toString());
        }
    }

    private List<String> findPointMutations(String geneSequence, String inputSequence) {
        List<String> mutations = new ArrayList<>();

        // Check for differences between the sequences
        for (int i = 0; i < geneSequence.length() && i < inputSequence.length(); i++) {
            if (geneSequence.charAt(i) != inputSequence.charAt(i)) {
                mutations.add("Position: " + (i + 1) + ", Mutation: " + geneSequence.charAt(i) + " -> " + inputSequence.charAt(i));
            }
        }

        return mutations;

    }


    @FXML
    private TextField givenSeqTF;

    @FXML
    private TextArea mutationTA;
    @FXML
    private Button showMutationButton;

}
