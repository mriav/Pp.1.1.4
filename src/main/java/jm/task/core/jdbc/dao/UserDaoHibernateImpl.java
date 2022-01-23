package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL," +
                "lastname VARCHAR(45) NOT NULL, " +
                "age TINYINT(3) NOT NULL, " +
                "PRIMARY KEY (id));").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id= :id")
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE users;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}

