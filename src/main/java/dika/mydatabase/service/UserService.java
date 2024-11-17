package dika.mydatabase.service;

import dika.mydatabase.model.User;

import java.util.List;

public interface UserService {

    void saveUser(String name, String lastName, byte age);

    void createUsersTable();

    void dropUsersTable();

    void removeUserById(long id) throws Exception;

    List<User> getAllUsers();

    void cleanUsersTable();
}
