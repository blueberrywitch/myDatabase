package dika.mydatabase;

import dika.mydatabase.dao.UserDaoHibernateImpl;
import dika.mydatabase.dao.UserDaoJDBCImpl;
import dika.mydatabase.service.UserServiceJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MyDatabaseApplication {

    private static final Logger log = LoggerFactory.getLogger(MyDatabaseApplication.class);
    private static MyDatabaseApplication instance;

    public static MyDatabaseApplication getInstance() throws SQLException {
        if (instance == null) {
            instance = new MyDatabaseApplication();
        }
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (UserDaoHibernateImpl userDaoHibernateImpl = UserDaoHibernateImpl.getInstance()) {


            userDaoHibernateImpl.saveUser("Dika", "Dikov", (byte) 25);

            userDaoHibernateImpl.saveUser("Vlad", "Kolesnikov", (byte)19);
            userDaoHibernateImpl.saveUser("Amir", "Matygulin", (byte)27);
            userDaoHibernateImpl.saveUser("Lera", "Stepchenkova", (byte)21);
            userDaoHibernateImpl.saveUser("Dina", "SDd", (byte)1);

            userDaoHibernateImpl.removeUserById(234);

            userDaoHibernateImpl.getAllUsers().forEach(System.out::println);

            userDaoHibernateImpl.cleanUsersTable();

            userDaoHibernateImpl.getAllUsers().forEach(System.out::println);

            userDaoHibernateImpl.dropUsersTable();
        } catch (Exception e) {
            System.out.println("Error initializing UserServiceJDBC: " + e.getMessage());
        }

    }
}