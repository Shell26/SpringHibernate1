package shell.Dao;

import shell.model.Role;

public interface RoleDao {
    Role getRoleById(Long roleId);
    Role getRoleUser();
}
