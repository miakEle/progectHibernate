package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;
    private final Logger logger;

    public UserDaoHibernateImpl() {
        logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45), " +
                    "lastName VARCHAR(45), " +
                    "age INT, " +
                    "PRIMARY KEY(id));").executeUpdate();
            transaction.commit();
            logger.log(Level.INFO, "Таблица Users успешно создана");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User")
                    .executeUpdate();
            transaction.commit();
            logger.log(Level.INFO, "Таблица Users успешно удалена");
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            logger.log(Level.INFO, "User с именем – {0} добавлен в базу данных", name);
        } catch (HibernateException e) {
            e.printStackTrace();
           // transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            logger.log(Level.INFO, "User с id – {0} удален из базы данных", id);
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "User с id – {0} не найден", id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List list = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("FROM User ").list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            //transaction.rollback();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE User").executeUpdate();
            transaction.commit();
            logger.log(Level.INFO, "Таблица Users успешно очищена");
        } catch (HibernateException e) {
            e.printStackTrace();
            //transaction.rollback();
        }
    }
}