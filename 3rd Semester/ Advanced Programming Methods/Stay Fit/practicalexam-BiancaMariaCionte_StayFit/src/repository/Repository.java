package repository;

import domain.Fitness;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private List<Fitness> fitnesses = new ArrayList<>();

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

    public List<Fitness> fetchSortedFitnessFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Fitness";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                Integer steps = resultSet.getInt("noOfSteps");
                Double sleep = resultSet.getDouble("hoursOfSleep");
                List<String> physicalA = Collections.singletonList(resultSet.getString("physicalActivities"));

                Fitness fit = new Fitness(date,steps,sleep,physicalA);
                fitnesses.add(fit);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Fitness> sortedFit = fitnesses.stream()
                .sorted(Comparator.comparing(Fitness::getDate))
                .collect(Collectors.toList());
        return sortedFit;
    }

    public List<Fitness> searchFitnessBy(Integer number)
    {
        return fitnesses.stream()
                .filter(fitness -> getTotalMoveMinutes(fitness)>number)
                .collect(Collectors.toList());
    }

    private int getTotalMoveMinutes(Fitness fitness) {
        int totalMoveMinutes = 0;
        for (String activity : fitness.getPhysicalActivities()) {
            String[] activities = activity.split(",");
            for(String act: activities)
            {
                String [] parts = act.trim().split("\\s+");
                if (parts.length>=2) {
                    try {
                        totalMoveMinutes += Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return totalMoveMinutes;
    }

    public void add(Fitness r) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String query = "INSERT INTO Fitness (date, noOfSteps, hoursOfSleep, physicalActivities) " +
                    "VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, r.getDate());
                preparedStatement.setInt(2, r.getNoOfSteps());
                preparedStatement.setDouble(3, r.getHoursOfSleep());
                preparedStatement.setString(4, String.valueOf(r.getPhysicalActivities()));

                preparedStatement.executeUpdate();
            }

            fitnesses.add(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fitness> getAll()
    {
        return fitnesses;
    }

    public Integer noOfStepsByMonth(String month)
    {
        Integer noOfSteps = 0;
        for(Fitness f:fitnesses)
        {
            if(f.getDate().substring(5, 7).equals(month))
            {
                noOfSteps +=f.getNoOfSteps();
            }
        }
        return noOfSteps;
    }

}
