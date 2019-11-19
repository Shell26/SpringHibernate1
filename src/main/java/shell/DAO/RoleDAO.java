package shell.DAO;


import shell.model.Role;

public interface RoleDAO {

    Role getRoleById(Long roleId);
    Role getRoleUser();
}
