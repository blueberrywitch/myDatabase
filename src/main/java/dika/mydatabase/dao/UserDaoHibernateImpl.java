package dika.mydatabase.dao;

import dika.mydatabase.model.User;
import dika.mydatabase.util.MyException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


@Slf4j
public class UserDaoHibernateImpl implements UserDao, AutoCloseable {

    private SessionFactory factory;
    static UserDaoHibernateImpl instance;
    public UserDaoHibernateImpl() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static UserDaoHibernateImpl getInstance() {
        if (instance == null || instance.factory == null || instance.factory.isClosed()) {
            instance = new UserDaoHibernateImpl();
        }
        return instance;
    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();

            transaction.commit();
            System.out.println("Table 'users' dropped successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        log.info("user saved");
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) throws Exception {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            User user = session.find(User.class, id);
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            log.info("user removed");
        } catch (IllegalArgumentException e){
            System.out.println("нет юзера с таким айди");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        factory.close();
    }
}