package repository;

import domain.Genes;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Repository
{
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection()
    {
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
        catch (SQLException e)
        {
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

    public ArrayList<Genes> getAll()
    {
        ArrayList<Genes> documents = new ArrayList<>();
        try {
            openConnection();
            // SQL query to select all rows from the "documents" table
            String selectString = "SELECT * FROM Genes;";
            try (PreparedStatement ps = conn.prepareStatement(selectString))
            {
                ResultSet resultSet = ps.executeQuery();

                // Iterate through the result set and create Document objects
                while (resultSet.next())
                {
                    String name = resultSet.getString("name");
                    String organism = resultSet.getString("organism");
                    String function = resultSet.getString("function");
                    String associated = resultSet.getString("associated");

                    Genes document = new Genes(name, organism, function, associated);
                    documents.add(document);
                }
            }
        } catch (SQLException e)
        {
            // Wrap SQLException in a RuntimeException and rethrow
            throw new RuntimeException(e);
        }
        finally
        {
            // Close the database connection in the "finally" block to ensure it always happens
            closeConnection();

        }
        return documents;
    }

    public void update(Genes gene, String  newFunction, String newSequence) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:data/test_db.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            PreparedStatement ps = connection.prepareStatement("update Genes set function=?, associated=? where name=? and organism=?");
            ps.setString(1, newFunction);
            ps.setString(2, newSequence);
            ps.setString(3, gene.getName());
            ps.setString(4, gene.getOrganism());
            ps.executeUpdate();
            ps.close();

            System.out.println("Gene updated");


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
