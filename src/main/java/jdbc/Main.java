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

        userDao.saveUser("Ivan", "Takaseev", (byte) 62);
        userDao.saveUser("Petya", "Peterson", (byte) 32);
        userDao.saveUser("Fedorov", "Kalashnikov", (byte) 44);
        userDao.saveUser("Tanya", "Potterson", (byte) 20);
        userDao.removeUserById(2);
        System.out.println(userDao.getAllUsers().toString());

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
