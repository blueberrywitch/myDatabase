package dika.mydatabase.service;

import dika.mydatabase.model.User;

import java.util.List;

public interface UserService {

    void addUser(String name, String lastName, int age);

    void createUsersTable();

    void dropUsersTable(String tableName);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
