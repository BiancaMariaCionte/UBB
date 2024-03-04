package service;

import domain.Genes;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Service
{
    private Repository repo;

    public Service(Repository repo)
    {
        this.repo = repo;
    }

    public List<Genes> getAll()
    {
        return repo.getAll();
    }

    public void update(Genes gene, String  newFunction, String newSequence)
    {
        repo.update(gene,newFunction,newSequence);
    }
}
