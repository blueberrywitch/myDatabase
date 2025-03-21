package dika.mydatabase.service;

import dika.mydatabase.exceptions.TableNotDropedException;
import dika.mydatabase.exceptions.UserNotRemovedException;
import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.model.User;

import java.util.List;

public interface UserService {

    void saveUser(String name, String lastName, byte age) throws UserNotSavedException;

    void createUsersTable();

    void dropUsersTable();

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
