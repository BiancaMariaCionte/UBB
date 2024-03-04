package repository;

import domain.Quiz;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private List<Quiz> quizzes = new ArrayList<>();

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

    public List<Quiz> fetchSortedRoutesFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Quiz";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                String correctAnswer = resultSet.getString("correctAnswer");
                Integer score = resultSet.getInt("score");
                String userAnswer = resultSet.getString("userAnswer");


                Quiz quiz = new Quiz(id,text,correctAnswer,score,userAnswer);
                quizzes.add(quiz);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Quiz> sortedQuestions = quizzes.stream()
                .sorted(Comparator.comparing(Quiz::getScore))
                .collect(Collectors.toList());
        return sortedQuestions;
    }



    public List<Quiz> searchQuestions(String text, Integer minScore)
    {
        return quizzes.stream()
                .filter(quiz -> quiz.getText().toLowerCase().contains(text.toLowerCase())
                && quiz.getScore()>minScore)
                .collect(Collectors.toList());

    }

    public void updateQuiz(Integer id, String text, String correctAnswer, Integer score, String userAnswer, String newUserAnswer) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:data/test_db.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            PreparedStatement ps = connection.prepareStatement("update Quiz set userAnswer=? where id=? and text=? and correctAnswer=? and score=? and userAnswer=?");
            ps.setString(1, newUserAnswer);
            ps.setInt(2, id);
            ps.setString(3, text);
            ps.setString(4, correctAnswer);
            ps.setInt(5, score);
            ps.setString(6, userAnswer);
            ps.executeUpdate();
            ps.close();

            System.out.println("Quiz updated");


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
}
