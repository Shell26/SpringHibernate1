package shell.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shell.model.User;
import shell.util.DBHelper;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

//    private SessionFactory sessionFactory;
//
//    public UserDaoImpl() {
//        sessionFactory = DBHelper.getSessionFactory();
//    }

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByLogin(String login) {
        Query query = (Query) entityManager.createQuery("select e from User e where e.login = :log");
        query.setParameter("log", login);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        Query query = (Query) entityManager.createQuery("select e from User e");
        users = query.getResultList();
        return users;
    }


    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User newUser, Long id) {
        User user = getById(id);
        user.setLogin(newUser.getLogin());
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        user.setRoles(newUser.getRoles());
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = getById(id);
        entityManager.remove(user);
    }
}
