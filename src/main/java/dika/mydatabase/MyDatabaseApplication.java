package dika.mydatabase;

import dika.mydatabase.exceptions.TableNotCleanedException;
import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.service.UserService;
import dika.mydatabase.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MyDatabaseApplication {

    public static void main(String[] args) throws UserNotSavedException {

        UserService userService = new UserServiceImpl();

        userService.saveUser("Dika", "Di", (byte) 25);
        userService.saveUser("Vlad", "Kolesnikov", (byte) 19);
        userService.saveUser("Amir", "Matygulin", (byte) 27);
        userService.saveUser("Lera", "Stepchenkova", (byte) 21);
        userService.saveUser("Dina", "SDd", (byte) 1);
        userService.removeUserById(10);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}