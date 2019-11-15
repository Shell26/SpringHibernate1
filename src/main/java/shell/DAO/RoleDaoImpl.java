package shell.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import shell.model.Role;
import shell.util.DBHelper;

import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    public RoleDaoImpl() {
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


    @Override
    public Role getRoleById(Long roleId) {
        Session session = sessionFactory.openSession();
        Role role = (Role)session.load(Role.class, roleId);
        return role;
    }
}

