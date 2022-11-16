package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    Session session = null;

    @Override
    public void createUsersTable() {
        try {
            if (session == null || !session.isOpen()) {
                session = Util.getSessionFactory().openSession();
            }
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS Users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastname VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            if (session == null || !session.isOpen()) {
                session = Util.getSessionFactory().openSession();
            }
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS Users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            if (session == null || !session.isOpen()) {
                session = Util.getSessionFactory().openSession();
            }
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            if (session == null || !session.isOpen()) {
                session = Util.getSessionFactory().openSession();
            }
            session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        try {
            if (session == null || !session.isOpen()) {
                session = Util.getSessionFactory().openSession();
            }
            session.beginTransaction();
            userList = session.createQuery("from Users", User.class).getResultList();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            if (session == null || !session.isOpen()) {
                session = Util.getSessionFactory().openSession();
            }
            Transaction transaction = session.beginTransaction();
            String sql = "Delete from Users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}