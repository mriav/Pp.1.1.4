package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao UserDaoHibernate = new UserDaoHibernateImpl();

    public UserServiceImpl() throws SQLException{
    }

    public void createUsersTable() {
        UserDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoHibernate.cleanUsersTable();
    }
}
