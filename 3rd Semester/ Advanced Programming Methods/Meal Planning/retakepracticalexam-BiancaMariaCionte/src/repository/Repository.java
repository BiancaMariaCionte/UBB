package repository;

import domain.Recipe;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;
    private List<Recipe> recipeList = new ArrayList<>();

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

    public List<Recipe> fetchSortedFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Recipe";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Integer cookingTime = resultSet.getInt("cookingTime");
                List<String> ingredients = Collections.singletonList(resultSet.getString("ingredients"));

                Recipe recipe = new Recipe(name,cookingTime, ingredients);
                recipeList.add(recipe);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Recipe> sortedRecipes = recipeList.stream()
                .sorted(Comparator.comparing(Recipe::getName))
                .collect(Collectors.toList());
        return sortedRecipes;
    }

    public List<Recipe> filterRecipes(Integer cooking, String ingredient)
    {
        return recipeList.stream()
                .filter(recipe -> recipe.getCookingTime()<cooking && recipe.getIngredients().stream().anyMatch(keyword->keyword.toLowerCase().contains(ingredient)))
                .collect(Collectors.toList());
    }

    public void add(Recipe r) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String query = "INSERT INTO Recipe (name, cookingTime, ingredients) " +
                    "VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, r.getName());
                preparedStatement.setInt(2, r.getCookingTime());
                preparedStatement.setString(3, String.valueOf(r.getIngredients()));

                preparedStatement.executeUpdate();
            }

            recipeList.add(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> getAll()
    {
        return recipeList;
    }




//    public void update(Recipe Recipe, Integer newPreci, String newDescription) {
//        Connection connection = null;
//        try {
//            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:data/test_db.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            PreparedStatement ps = connection.prepareStatement("update Recipe set precipitationProbability=?, description=? where startHour=? and endHour=? and temperature=?");
//            ps.setInt(1, newPreci);
//            ps.setString(2, newDescription);
//            ps.setInt(3, Recipe.getStartHour());
//            ps.setInt(4, Recipe.getEndHour());
//            ps.setInt(5, Recipe.getTemperature());
//            ps.executeUpdate();
//            ps.close();
//
//            System.out.println("Recipe updated");
//
//
//        } catch (SQLException e) {
//            // if the error message is "out of memory",
//            // it probably means no database file is found
//            System.err.println(e.getMessage());
//        }
//        finally
//        {
//            try
//            {
//                if(connection != null)
//                    connection.close();
//            }
//            catch(SQLException e)
//            {
//                // connection close failed.
//                System.err.println(e.getMessage());
//            }
//        }
//    }
}
