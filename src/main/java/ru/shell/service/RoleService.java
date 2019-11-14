package ru.shell.service;

import ru.shell.model.Role;

public interface RoleService {
    Role getById(Long id);
    Role getUserRole(String roleName);
}
