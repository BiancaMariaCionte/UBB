package repository;

import domain.Route;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private List<Route> routes = new ArrayList<>();

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

    public List<Route> fetchSortedRoutesFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Route";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String sourceCity = resultSet.getString("sourceCity");
                String destinationCity = resultSet.getString("destinationCity");
                Double departureTime = resultSet.getDouble("departureTime");
                Double arrivalTime = resultSet.getDouble("arrivalTime");
                Integer noOfSeats = resultSet.getInt("noOfSeats");
                Double price = resultSet.getDouble("price");


                Route route = new Route(sourceCity,destinationCity,departureTime,arrivalTime,noOfSeats,price);
                routes.add(route);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Route> sortedRoutes = routes.stream()
                .sorted(Comparator.comparing(Route::getSourceCity))
                .sorted(Comparator.comparing(Route::getDepartureTime))
                .collect(Collectors.toList());
        return sortedRoutes;
    }

    public List<Route> getAll()
    {
        return routes;
    }

    public List<Route> showAvailableRoutes(String  source, String destination)
    {
        return routes.stream()
                .filter(route -> route.getSourceCity().equals(source)&& route.getDestinationCity().equals(destination))
                .collect(Collectors.toList());
    }

    public void updateAvailableTickets(Route route, Integer nr)
    {
        route.setNoOfSeats(route.getNoOfSeats()-nr);
    }

}
