package dika.mydatabase.dao;

import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age) throws UserNotSavedException;

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
