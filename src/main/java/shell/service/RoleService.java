package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shell.DAO.RoleDAO;
import shell.model.Role;

@Component
public class RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Role getRoleById(Long roleId) {
        return roleDAO.getRoleById(roleId);
    }

    public Role getRoleUser() {
        return roleDAO.getRoleUser();
    }
}
