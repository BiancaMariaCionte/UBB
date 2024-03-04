package repository;

import domain.Weather;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    List<Weather> weathers = new ArrayList<>();

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

    public List<Weather> fetchSortedWeathersFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Weather";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer startHour = resultSet.getInt("startHour");
                Integer endHour = resultSet.getInt("endHour");
                Integer temperature = resultSet.getInt("temperature");
                Integer precipitationProbability = resultSet.getInt("precipitationProbability");
                String description = resultSet.getString("description");


                Weather w = new Weather(startHour,endHour,temperature,precipitationProbability,description);
                weathers.add(w);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Weather> sortedWeathers = weathers.stream()
                .sorted(Comparator.comparing(Weather::getStartHour))
                .collect(Collectors.toList());
        return sortedWeathers;
    }

    public List<Weather> getAll()
    {
        return weathers;
    }

    public void updateWeather(Weather weather, Integer newPrecipitation, String newDescription) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:data/test_db.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            PreparedStatement ps = connection.prepareStatement("update Weather set precipitationProbability=? , description=? where startHour=? and endHour=? and temperature=?");
            ps.setInt(1, newPrecipitation);
            ps.setString(2, newDescription);
            ps.setInt(3, weather.getStartHour());
            ps.setInt(4, weather.getEndHour());
            ps.setInt(5, weather.getTemperature());
            ps.executeUpdate();
            ps.close();

            System.out.println("Weather updated");


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

    public List<Weather> searchWeather(String inputWord)
    {
        return weathers.stream()
                .filter(weather -> weather.getDescription().toLowerCase().contains(inputWord.toLowerCase()))
                .collect(Collectors.toList());

    }

}
