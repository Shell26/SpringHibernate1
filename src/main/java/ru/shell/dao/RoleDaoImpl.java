package ru.shell.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shell.model.Role;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Role getById(Long id) {
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class, id);
        session.close();
        return role;
    }

    @Override
    public Role getUserRole(String roleName) {
        Session session = sessionFactory.openSession();
        Role role;
        List<Role> roles = session.createQuery("FROM Role").list();
        role = roles.get(0);
        session.close();
        return role;
    }
}
