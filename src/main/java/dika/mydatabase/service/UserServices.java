package dika.mydatabase.service;

import dika.mydatabase.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserServices implements UserService {

    @Override
    public void addUser(String name, String lastName, int age) {
        String sql = "INSERT INTO employees (name, lastName,age) VALUES (?, ?, ?)";

        try (Connection conn = UserService.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setDouble(3, age);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user has been inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS employees ("
                + " id serial PRIMARY KEY,"
                + " name VARCHAR(100) NOT NULL,"
                + " lastName VARCHAR(50),"
                + " age NUMERIC"
                + ");";

        try (Connection conn = UserService.connection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable(String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName;

        try (Connection conn = UserService.connection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Table " + tableName + " has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting table: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = UserService.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id); // Подставляем значение в параметр
            int resultSet = pstmt.executeUpdate();

            if (resultSet > 0){
                System.out.println("User with ID " + id + " was successfully removed.");
            } else {
                System.out.println("User with ID " + id + " does not exist.");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM employees";
        List<User> users = new ArrayList<>();

        try (Connection conn = UserService.connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getInt("age"));
                users.add(user);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM employees";

        try (Connection conn = UserService.connection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table cleaned successfully.");
        } catch (SQLException e) {
            System.out.println("Error cleaning table: " + e.getMessage());
        }
    }
}


