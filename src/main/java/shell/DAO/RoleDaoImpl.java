package shell.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shell.model.Role;
import shell.util.DBHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

//    private SessionFactory sessionFactory;
//
//    public RoleDaoImpl() {
//        sessionFactory = DBHelper.getSessionFactory();
//    }

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleUser() {
        return entityManager.find(Role.class, 2L);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return entityManager.find(Role.class, roleId);
    }
}

