package dika.mydatabase.service;

import dika.mydatabase.dao.UserDao;
import dika.mydatabase.dao.UserDaoHibernateImpl;
import dika.mydatabase.exceptions.TableNotCleanedException;
import dika.mydatabase.exceptions.TableNotDropedException;
import dika.mydatabase.exceptions.UserNotRemovedException;
import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.exceptions.UsersNotGotException;
import dika.mydatabase.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        try{
            userDao.dropUsersTable();
        } catch (TableNotDropedException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDao.saveUser(name, lastName, age);
        } catch (UserNotSavedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User saved " + name + " " + lastName + " " + age);
    }

    public void removeUserById(long id){
        try{
            userDao.removeUserById(id);
        }
        catch(UserNotRemovedException e){
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        try{
            return userDao.getAllUsers();
        } catch (UsersNotGotException e){
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        try{
            userDao.cleanUsersTable();
        } catch (TableNotCleanedException e){
            throw new RuntimeException(e);
        }

    }
}