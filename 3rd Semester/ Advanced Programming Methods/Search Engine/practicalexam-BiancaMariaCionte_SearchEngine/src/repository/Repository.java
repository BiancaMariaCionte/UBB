package repository;

import domain.SearchEngine;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private List<SearchEngine> searchEngines = new ArrayList<>();

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Repository() {}

    public List<SearchEngine> fetchSortedRoutesFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Documents";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                List<String> keywords = Collections.singletonList(resultSet.getString("keywords"));
                String content = resultSet.getString("content");

                SearchEngine se = new SearchEngine(name,keywords,content);
                searchEngines.add(se);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<SearchEngine> sortedEngines = searchEngines.stream()
                .sorted(Comparator.comparing(SearchEngine::getName))
                .collect(Collectors.toList());
        return sortedEngines;
    }

    public List<SearchEngine> searchDocumentBy(String text)
    {
        return searchEngines.stream()
                .filter(document -> document.getName().toLowerCase().contains(text) || document.getKeywords().stream().anyMatch(keyword -> keyword.toLowerCase().contains(text)))
                .collect(Collectors.toList());
    }


    public void updateDocument(SearchEngine se, List<String> newKeywords, String newContent) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:data/test_db.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            PreparedStatement ps = connection.prepareStatement("update Documents set keywords=?, content=? where name=?");
            ps.setString(1, String.valueOf(newKeywords));
            ps.setString(2, newContent);
            ps.setString(3, se.getName());
            ps.executeUpdate();
            ps.close();

            System.out.println("Document updated");


        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public double computeSimilarity(String s1, String s2)
    {
        //here we need the min length because we need to compare the existing common letters between the two words obviously
        int minLength = Math.min(s1.length(), s2.length());
        int commonLetters = 0;
        for(int i=0;i<minLength;i++)
        {
            if(s1.charAt(i)==s2.charAt(i))
            {
                commonLetters++;
            }
        }
        return (double) commonLetters / (double) Math.max(s1.length(),s2.length());
    }


    public SearchEngine findBestMatchingDocument(String searchText)
    {
        SearchEngine bestMatch = null;
        double maxSimilarity = -1.0;
        for(SearchEngine document: searchEngines)
        {
            double similarity = computeSimilarity(searchText,document.getName().toLowerCase());
            if(similarity>maxSimilarity)
            {
                maxSimilarity=similarity;
                bestMatch = document;
            }

        }
        return bestMatch;
    }




}
