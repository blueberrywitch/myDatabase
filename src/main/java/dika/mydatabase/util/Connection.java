package dika.mydatabase.util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection implements AutoCloseable {

    public static java.sql.Connection connection() {
        java.sql.Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/mydatabase";
            String user = "user";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection() != null) {
            try {
                connection().close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
