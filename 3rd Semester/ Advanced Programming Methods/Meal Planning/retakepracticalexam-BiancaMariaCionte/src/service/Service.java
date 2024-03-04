package service;

import domain.Recipe;
import repository.Repository;

import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Recipe> fetchSortedFromDatabase()
    {
        return repo.fetchSortedFromDatabase();
    }

    public List<Recipe> filterRecipes(Integer cooking, String ingredient)
    {
        return repo.filterRecipes(cooking,ingredient);
    }

    public void add(Recipe r)
    {
        repo.add(r);
    }

    public List<Recipe> getAll()
    {
        return repo.getAll();
    }

//    public void update(Weather weather, Integer newPreci, String newDescription)
//    {
//        repo.update();
//    }
}
