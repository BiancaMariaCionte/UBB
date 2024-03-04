package service;

import domain.Quiz;
import repository.Repository;

import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Quiz> getAllQuestions(){return repo.fetchSortedRoutesFromDatabase();}

    public List<Quiz> searchQuestions(String text, Integer score)
    {
        return repo.searchQuestions(text,score);
    }

    public void updateQuiz(Integer id, String text, String correctAnswer, Integer score, String userAnswer, String newUserAnswer) {
        repo.updateQuiz(id, text, correctAnswer, score, userAnswer, newUserAnswer);
    }


}
