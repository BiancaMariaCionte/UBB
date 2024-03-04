package service;

import domain.Fitness;
import repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Fitness> getAllFitness(){return repo.fetchSortedFitnessFromDatabase();}



    public List<Fitness> searchFitnessBy(Integer text)
    {
       return repo.searchFitnessBy(text);
    }

    public void add(Fitness r){repo.add(r); }

    public List<Fitness> getAll()
    {
        return repo.getAll();
    }

    public Integer noOfStepsByMonth(String month)
    {
        return repo.noOfStepsByMonth(month);
    }

}


