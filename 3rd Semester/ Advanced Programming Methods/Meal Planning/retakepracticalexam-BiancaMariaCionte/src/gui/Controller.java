package gui;

import domain.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import repository.Repository;
import service.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }
    @FXML
    private ListView<Recipe> recipeListView;

    public void populateList()
    {
        ObservableList<Recipe> weathers = FXCollections.observableArrayList(service.fetchSortedFromDatabase());
        recipeListView.setItems(weathers);
        recipeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//        ObservableList<String> newList = FXCollections.observableArrayList();
//        for(Weather w:weathers)
//        {
//            String description = w.getDescription();
//            String firstWord = description.split(",")[0].trim();
//            if(!newList.contains(firstWord)){
//                newList.add(firstWord);
//            }
//
//        }
//        descriptionsCB.setItems(newList);
//        if(totalHoursText.getText().equals("Text"))
//        {
//            totalHoursText.setText("0");
//        }
    }
    public void initialize()
    {
        populateList();
    }

    @FXML
    private Button searchButton;
    @FXML
    private TextField cookingTF;

    @FXML
    private TextField ingredientTF;

    @FXML
    void searchButtonClicked(MouseEvent event) {
        Integer time = Integer.parseInt(cookingTF.getText());
        String ingredient = ingredientTF.getText();
        ObservableList<Recipe> filteredList = FXCollections.observableArrayList(service.filterRecipes(time,ingredient));
        recipeListView.setItems(filteredList);

    }

    @FXML
    private Button addButton;

    @FXML
    private TextField addCookingTimeTF;

    @FXML
    private TextField addIngredientsTF;


    @FXML
    private TextField nameTF;

    @FXML
    void addButtonClicked(MouseEvent event) {
        String name = nameTF.getText();
        Integer time = Integer.parseInt(addCookingTimeTF.getText());
        List<String> ingredients = Collections.singletonList(addIngredientsTF.getText());
        Recipe recipe = new Recipe(name,time,ingredients);

        service.add(recipe);
        ObservableList<Recipe> fitnessObservableList = FXCollections.observableArrayList(service.getAll());
        recipeListView.setItems(fitnessObservableList);
    }

    @FXML
    private Button showButton;


    @FXML
    private TextArea shoppingListTA;

    @FXML
    void listViewSelect(MouseEvent event) {


//        List<String> ingredients = new ArrayList<>();
//        for(Recipe recipe:selectedItems)
//        {
//            if(!ingredients.contains(recipe.getIngredients()))
//            {
//                ingredients.add(String.valueOf(recipe.getIngredients()));
//            }
//        }
//        shoppingListTA.setText(String.valueOf(ingredients));



    }



        @FXML


        private Button button2;

      @FXML

        private TextField noTF;
      @FXML
        void button2Clicked(MouseEvent event)
    {
        Integer no = Integer.parseInt(noTF.getText());
        List<Recipe> ing = new ArrayList<>();
        for(int i=0;i<no;i++)
        {
            Recipe r= recipeListView.getSelectionModel().getSelectedItem();
             ing.add(r)  ;
        }

        List<String> ingredients = new ArrayList<>();
        for(Recipe r: ing)
        {
            if(!ingredients.contains(r.getIngredients()))
                ingredients.add(String.valueOf(r.getIngredients()));
        }

        shoppingListTA.setText(String.valueOf(ingredients));




    }

    @FXML
    void showButtonClicked(MouseEvent event) {

        ObservableList<Recipe> selectedItems = recipeListView.getSelectionModel().getSelectedItems();

        Recipe selectedItemm = recipeListView.getSelectionModel().getSelectedItem();
        List<String> listSe = new ArrayList<>();
        listSe.addAll(selectedItemm.getIngredients());




        List<String> ing = new ArrayList<>();
        for(Recipe r:selectedItems)
        {
                   ing.addAll(r.getIngredients());
        }
        String ingredientsText =String.join(",",ing);

        for(String elem:ing)
        {
            if(!ing.contains(elem))
            {
                listSe.add(elem);
            }
        }

//        Recipe recipe = recipeListView.getSelectionModel().getSelectedItem();
//        List<String> ingredients = new ArrayList<>();
       // ingredients.add(String.valueOf(recipe.getIngredients()))  ;


        shoppingListTA.setText(String.valueOf(listSe));
    }




}
