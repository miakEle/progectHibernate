package jdbc;
import jdbc.dao.UserDao;
import jdbc.dao.UserDaoHibernateImpl;
import jdbc.dao.UserDaoJDBCImpl;
import jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Elena", "Elena", (byte) 62);
        userDao.saveUser("Ivan", "Ivan", (byte) 32);
        userDao.saveUser("Marja", "Marja", (byte) 44);
        userDao.saveUser("Sonja", "Sonja", (byte) 20);
        userDao.removeUserById(2);
        System.out.println(userDao.getAllUsers().toString());

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
