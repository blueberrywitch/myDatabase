package dika.mydatabase.service;

import dika.mydatabase.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

    static java.sql.Connection connection() {
        Connection connection = null;
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

    void addUser(String name, String lastName, int age);

    void createUsersTable();

    void dropUsersTable(String tableName);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
