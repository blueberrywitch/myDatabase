package dika.mydatabase.dao;

import dika.mydatabase.exceptions.TableNotCleanedException;
import dika.mydatabase.exceptions.TableNotDropedException;
import dika.mydatabase.exceptions.UserNotRemovedException;
import dika.mydatabase.exceptions.UsersNotGotException;
import dika.mydatabase.model.User;
import dika.mydatabase.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao, AutoCloseable {

    private static UserDaoJDBCImpl instance;
    private final Connection conn;

    public UserDaoJDBCImpl() {
        this.conn = DatabaseConnection.connection();
    }

    public static UserDaoJDBCImpl getInstance() throws SQLException {
        if (instance == null || instance.conn.isClosed()) {
            instance = new UserDaoJDBCImpl();
        }
        return instance;
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName,age) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

        String sql = "CREATE TABLE IF NOT EXISTS users (" + " id serial PRIMARY KEY," + " name VARCHAR(100) NOT NULL," + " lastName VARCHAR(50)," + " age NUMERIC" + ");";

        try (Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() throws TableNotDropedException {
        String sql = "DROP TABLE IF EXISTS " + "users";

        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Table " + "users" + " has been deleted successfully.");
        } catch (SQLException e) {
            throw new TableNotDropedException("Table not dropped", e);
        }
    }

    @Override
    public void removeUserById(long id) throws UserNotRemovedException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id); // Подставляем значение в параметр
            int resultSet = pstmt.executeUpdate();

            if (resultSet > 0) {
                System.out.println("User with ID " + id + " was successfully removed.");
            } else {
                System.out.println("User with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            throw new UserNotRemovedException("User not removed", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws UsersNotGotException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new UsersNotGotException("Users not got", e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() throws TableNotCleanedException {
        String sql = "DELETE FROM users";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table cleaned successfully.");
        } catch (SQLException e) {
            throw new TableNotCleanedException("Table not cleaned", e);
        }
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}



