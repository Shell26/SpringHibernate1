package ru.shell.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("from User").getResultList();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.persist(user);
        session.close();
    }

    @Override
    @Transactional
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        session.delete(user);
        session.close();
    }


    @Override
    @Transactional
    public void edit(User user) {
        Session session = sessionFactory.openSession();
        session.update(user);
        session.close();
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where name =: n");
        query.setParameter("n", name);
        List list = query.list();
        User user = (User) list.get(0);
        session.close();
        return user;
    }
}
