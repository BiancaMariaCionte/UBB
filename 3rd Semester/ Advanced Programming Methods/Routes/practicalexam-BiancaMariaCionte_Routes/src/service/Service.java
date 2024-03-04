package service;

import domain.Route;
import repository.Repository;

import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Route> getAll(){return repo.getAll();}

    public List<Route> getAllRouts(){return repo.fetchSortedRoutesFromDatabase();}

    public List<Route> showAvailableRoutes(String  source, String destination)
    {
        return repo.showAvailableRoutes(source,destination);
    }

    public void updateAvailableTickets(Route route, Integer nr)
    {
        repo.updateAvailableTickets(route,nr);
    }
}
