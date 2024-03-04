package gui;

import domain.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.Service;

public class Controller {
    private Service service;

    private boolean hintClicked = false;

    public Controller(Service service) {
        this.service = service;
    }
    @FXML
    private ListView<Quiz> quizListView;

    @FXML
    private TextField scoreTF;

    @FXML
    private Button searchButton;

    @FXML
    private TextField textTF;



    void populateList()
    {

        ObservableList<Quiz> questionsList = FXCollections.observableArrayList(service.getAllQuestions());
        quizListView.setItems(questionsList);

    }

    public void initialize()
    {
        if(totalScoreText.getText().equals("Text")) {
            totalScoreText.setText("0");
        }
        populateList();
    }

    @FXML
    void searchButtonClicked(MouseEvent event) {

        String text = textTF.getText();
        Integer score = Integer.parseInt(scoreTF.getText());
         ObservableList<Quiz> filteredList = FXCollections.observableArrayList(service.searchQuestions(text,score));
         quizListView.setItems(filteredList);
    }

    //////////////////////////////////////////////////////////////
    @FXML
    private Button answerButton;

    @FXML
    private TextField answerTF;

    @FXML
    private Text totalScoreText;

    @FXML
    void answerButtonClicked(MouseEvent event) {
        Quiz q = quizListView.getSelectionModel().getSelectedItem();
        String answer = answerTF.getText();
        Integer currentScore = Integer.parseInt(totalScoreText.getText());
        service.updateQuiz(q.getId(),q.getText(),q.getCorrectAnswer(),q.getScore(),q.getUserAnswer(),answer);
        if(answer.equals(q.getCorrectAnswer()))
        {
            if(hintClicked)
            {
                currentScore = currentScore + q.getScore()/2;
                totalScoreText.setText(String.valueOf(currentScore));
                hintClicked=false;
            }
            else{
                currentScore = currentScore + q.getScore();
                totalScoreText.setText(String.valueOf(currentScore));
                hintClicked = false;
            }

        }
    }

    /////////////////////////////////////////////////

    @FXML
    private Button hintButton;

    @FXML
    private Text hintText;
    @FXML
    void hintButtonClicked(MouseEvent event) {
        Quiz q = quizListView.getSelectionModel().getSelectedItem();
        hintClicked = true;
        hintText.setText(q.getCorrectAnswer().substring(0,2));
    }

}
