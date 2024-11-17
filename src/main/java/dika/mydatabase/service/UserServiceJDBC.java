package dika.mydatabase.service;

import dika.mydatabase.dao.UserDao;
import dika.mydatabase.dao.UserDaoHibernateImpl;
import dika.mydatabase.model.User;

import java.util.List;

public class UserServiceJDBC implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User saved " + name + " " + lastName + " " + age);
    }

    public void removeUserById(long id) throws Exception {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        for (User user : users) System.out.println(user);
        return users;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}