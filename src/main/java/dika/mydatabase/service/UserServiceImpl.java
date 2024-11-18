package dika.mydatabase.service;

import dika.mydatabase.dao.UserDao;
import dika.mydatabase.dao.UserDaoHibernateImpl;
import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDao.saveUser(name, lastName, age);
        } catch (UserNotSavedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User saved " + name + " " + lastName + " " + age);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}