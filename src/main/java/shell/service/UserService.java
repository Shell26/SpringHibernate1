package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shell.DAO.*;
import shell.model.Role;
import shell.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserService {

private UserDao userDAO;

@Autowired
private RoleService roleService;

    @Autowired
    public UserService( UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User getById(Long id) {
        return userDAO.getById(id);
    }

    public void save(User user, String roleUser) {
        Set<Role> role = new HashSet<>();
        if (roleUser.equals("ADMIN")) {
            role.add(roleService.getRoleById(2L));
            user.setRoles(role);
            userDAO.save(user);
        } else {
            role.add(roleService.getRoleById(1L));
            user.setRoles(role);
            userDAO.save(user);
        }

    }


    public User getByLogin(String userlogin) {
      User user = userDAO.getByLogin(userlogin);
      return user;
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }

    public void edit(User user, Long id) {
        userDAO.update(user, id);
    }


}
