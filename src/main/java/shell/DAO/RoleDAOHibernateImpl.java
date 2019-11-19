package shell.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import shell.model.Role;
import shell.util.DBHelper;

import java.util.List;

@Component
public class RoleDAOHibernateImpl implements RoleDAO{

    private SessionFactory sessionFactory;

    public RoleDAOHibernateImpl() {
        sessionFactory = DBHelper.getSessionFactory();
    }


    @Override
    public Role getRoleUser() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Role role = new Role();
        List<Role> roles = session.createQuery("FROM Role").list();
        transaction.commit();
        role = roles.get(0);
        session.close();
        return role;
    }


    //чтобы не выгружать все роли
    @Override
    public Role getRoleById(Long roleId) {
        Session session = sessionFactory.openSession();
       // Transaction transaction = session.beginTransaction();
        //Hibernate просто верит нам, что объект с данным Id существует в БД.
        // первый вызванный get или set у proxy-object сразу инициирует запрос select,
        // и если объекта с данным Id нет в базе,получим ObjectNotFoundException.
        // предназначение proxy-object — реализация отложенной загрузки.
        Role role = (Role)session.load(Role.class, roleId);
        return role;
    }
}

