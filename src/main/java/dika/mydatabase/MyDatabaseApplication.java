package dika.mydatabase;

import dika.mydatabase.dao.UserDaoHibernateImpl;
import dika.mydatabase.dao.UserDaoJDBCImpl;
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
        try (UserDaoHibernateImpl userDaoJDBCImpl = UserDaoHibernateImpl.getInstance()) {
            UserServiceJDBC userServiceJDBC = new UserServiceJDBC();
            userServiceJDBC.createUsersTable();

            userServiceJDBC.saveUser("Dika", "Dikov", (byte) 25);
            userServiceJDBC.saveUser("Vlad", "Kolesnikov", (byte)19);
            userServiceJDBC.saveUser("Amir", "Matygulin", (byte)27);
            userServiceJDBC.saveUser("Lera", "Stepchenkova", (byte)21);
            userServiceJDBC.saveUser("Dina", "SDd", (byte)1);

            userServiceJDBC.removeUserById(1);

            userServiceJDBC.getAllUsers().forEach(System.out::println);

            userServiceJDBC.cleanUsersTable();

            userServiceJDBC.getAllUsers().forEach(System.out::println);

            userServiceJDBC.dropUsersTable();
        } catch (Exception e) {
            System.out.println("Error initializing UserServiceJDBC: " + e.getMessage());
        }

    }
}