package shell.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import shell.model.User;
import shell.util.DBHelper;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImpl() {
        sessionFactory = DBHelper.getSessionFactory();
    }

    @Override
    public User get(Long id) {
        List<User> users = getAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByLogin(String userlogin) {
        List<User> users = getAll();
        for (User user : users) {
            if (user.getLogin().equals(userlogin)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }


    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User newUser) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(newUser);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = get(id);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
