package ru.shell.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shell.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    //сообщает Spring о том, что он должен покопаться у себя в контексте и подставить сюда подходящий бин.
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
//    @SupressWarning("unchecked")
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }


    @Override
    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where name =: n");
        query.setParameter("n", name);
        List list = query.list();
        User user = (User) list.get(0);
        session.close();
        return user;
    }
}
