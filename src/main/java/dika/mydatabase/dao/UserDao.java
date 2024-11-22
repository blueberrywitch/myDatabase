package dika.mydatabase.dao;

import dika.mydatabase.exceptions.TableNotCleanedException;
import dika.mydatabase.exceptions.TableNotDropedException;
import dika.mydatabase.exceptions.UserNotRemovedException;
import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.exceptions.UsersNotGotException;
import dika.mydatabase.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable() throws TableNotDropedException;

    void saveUser(String name, String lastName, byte age) throws UserNotSavedException;

    void removeUserById(long id) throws UserNotRemovedException;

    List<User> getAllUsers() throws UsersNotGotException;

    void cleanUsersTable() throws TableNotCleanedException;
}
