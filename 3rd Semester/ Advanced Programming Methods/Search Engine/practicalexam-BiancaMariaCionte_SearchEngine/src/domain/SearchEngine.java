package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.StringJoiner;

public class SearchEngine {
    private String name;
    private List<String> keywords;
    private String content;
    private Double similarity;

    public SearchEngine(String name, List<String> keywords, String content, Double similarity) {
        this.name = name;
        this.keywords = keywords;
        this.content = content;
        this.similarity = similarity;
    }

    public SearchEngine(String name, List<String> keywords, String content) {
        this.name = name;
        this.keywords = keywords;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SearchEngine{" +
                "name='" + name + '\'' +
                ", keywords=" + keywords +
                '}';
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }
}
