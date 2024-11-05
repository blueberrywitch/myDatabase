package dika.mydatabase;

import dika.mydatabase.service.UserServiceJDBC;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MyDatabaseApplication {

    private static MyDatabaseApplication instance;

    public static MyDatabaseApplication getInstance() throws SQLException {
        if (instance == null) {
            instance = new MyDatabaseApplication();
        }
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserServiceJDBC userServiceJDBC = UserServiceJDBC.getInstance();

        userServiceJDBC.createUsersTable();

        userServiceJDBC.addUser("Dika", "Dikov", 25);
        userServiceJDBC.addUser("Vlad", "Kolesnikov", 19);
        userServiceJDBC.addUser("Amir", "Matygulin", 27);
        userServiceJDBC.addUser("Lera", "Stepchenkova", 21);
        userServiceJDBC.addUser("Dina", "SDd", 1);

        userServiceJDBC.removeUserById(1);

        userServiceJDBC.getAllUsers().forEach(System.out::println);

        userServiceJDBC.cleanUsersTable();

        userServiceJDBC.getAllUsers().forEach(System.out::println);

        userServiceJDBC.dropUsersTable("employees");
    }
}