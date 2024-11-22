package dika.mydatabase.dao;

import dika.mydatabase.exceptions.TableNotCleanedException;
import dika.mydatabase.exceptions.TableNotDropedException;
import dika.mydatabase.exceptions.UserNotRemovedException;
import dika.mydatabase.exceptions.UserNotSavedException;
import dika.mydatabase.exceptions.UsersNotGotException;
import dika.mydatabase.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


@Slf4j
public class UserDaoHibernateImpl implements UserDao, AutoCloseable {

    static UserDaoHibernateImpl instance;
    private final SessionFactory factory;

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
    public void dropUsersTable() throws TableNotDropedException {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            log.info("Table 'users' dropped successfully.");
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                    transaction.rollback();
            }
           throw new TableNotDropedException("Table not dropped", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws UserNotSavedException {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            log.info("User saved with name {}", name);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserNotSavedException("User not saved", e);
        }
    }

    @Override
    public void removeUserById(long id) throws UserNotRemovedException {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user == null) {
                throw new UserNotRemovedException("User not found");
            }
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                try {
                    transaction.rollback(); // Откат изменений при ошибке
                    log.info("Transaction rolled back successfully.");
                } catch (Exception rollbackEx) {
                    log.error("Failed to rollback the transaction: {}", rollbackEx.getMessage(), rollbackEx);
                }
            }
            log.error("Failed to drop the table 'users': {}", e.getMessage(), e);
            throw new UserNotRemovedException("User not found", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws UsersNotGotException{
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        } catch (Exception e) {
            throw new UsersNotGotException("Users not got", e);
        }
    }

    @Override
    public void cleanUsersTable() throws TableNotCleanedException {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TableNotCleanedException("Table not cleaned", e);
        }
    }

    @Override
    public void close() throws Exception {
        factory.close();
    }
}