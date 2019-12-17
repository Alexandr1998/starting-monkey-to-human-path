package RPIS72.Zaimov.wdad.data.managers;

import java.sql.*;

public class JDBCDataManager {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver...");

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM authors";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nauthors:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String second_name = resultSet.getString("second_name");
            String birth_date = resultSet.getString("birth_date");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("first name: " + first_name);
            System.out.println("second name: " + second_name);
            System.out.println("date: " + birth_date);
        }

        sql = "SELECT * FROM books";

        resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nbooks:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("name");
            String second_name = resultSet.getString("description");
            String birth_date = resultSet.getString("print_year");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("name: " + first_name);
            System.out.println("description: " + second_name);
            System.out.println("print_year: " + birth_date);
        }

        sql = "SELECT * FROM genres";

        resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\ngenres:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("name");
            String second_name = resultSet.getString("description");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("name: " + first_name);
            System.out.println("description: " + second_name);
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();
        
    }
}
