package ru.shell.dao;

import ru.shell.model.Role;

public interface RoleDao {
    Role getById(Long id);
    Role getUserRole(String roleName);
}
