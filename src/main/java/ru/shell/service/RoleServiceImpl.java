package ru.shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shell.dao.RoleDao;
import ru.shell.dao.UserDao;
import ru.shell.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void RoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public Role getUserRole(String roleName) {
        return roleDao.getUserRole(roleName);
    }
}
