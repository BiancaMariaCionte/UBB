package service;

import domain.Weather;
import repository.Repository;

import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Weather> getAll(){return repo.fetchSortedWeathersFromDatabase();}

    public List<Weather> getAllWeather(){return repo.getAll();}

    public void updateWeather(Weather weather, Integer newPrecipitation, String newDescription) {
        repo.updateWeather(weather,newPrecipitation,newDescription);
    }

    public List<Weather> searchWeather(String inputWord)
    {
        return repo.searchWeather(inputWord);
    }
}
