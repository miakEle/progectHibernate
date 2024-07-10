package jdbc.service;

import jdbc.dao.UserDao;
import jdbc.dao.UserDaoHibernateImpl;
import jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  userDao.getAllUsers();;
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
