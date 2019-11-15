package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shell.Dao.RoleDao;
import shell.model.Role;

@Component
public class RoleService {

    private RoleDao roleDAO;

    @Autowired
    public RoleService(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Role getRoleById(Long roleId) {
        return roleDAO.getRoleById(roleId);
    }

    public Role getRoleUser() {
        return roleDAO.getRoleUser();
    }
}
