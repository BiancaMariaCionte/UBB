package service;

import domain.SearchEngine;
import repository.Repository;

import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<SearchEngine> getAllQuestions(){return repo.fetchSortedRoutesFromDatabase();}

    public List<SearchEngine> searchDocumentBy(String text)
    {
        return repo.searchDocumentBy(text);
    }

    public void updateDocument(SearchEngine se,List<String> newKeywords, String newContent) {
        repo.updateDocument(se,newKeywords,newContent);
    }

    public double computeSimilarity(String s1, String s2)
    {

        return repo.computeSimilarity(s1,s2);
    }

    public SearchEngine findBestMatchingDocument(String searchText)
    {
        return repo.findBestMatchingDocument(searchText);
    }


}
