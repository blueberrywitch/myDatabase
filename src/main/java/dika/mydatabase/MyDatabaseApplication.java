package dika.mydatabase;

import dika.mydatabase.service.UserService;
import dika.mydatabase.service.UserServices;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MyDatabaseApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserServices userService = new UserServices();

        userService.createUsersTable();

        userService.addUser("Dika", "Dikov", 25);
        userService.addUser("Vlad", "Kolesnikov", 19);
        userService.addUser("Amir", "Matygulin", 27);
        userService.addUser("Lera", "Stepchenkova", 21);
        userService.addUser("Dina", "SDd", 1);

        userService.removeUserById(1);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.getAllUsers().forEach(System.out::println);

       userService.dropUsersTable("employees");
    }
}