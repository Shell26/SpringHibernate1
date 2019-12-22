package shell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shell.model.User;
import shell.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

//import shell.DAO.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    public void addUser(User user) { userRepository.save(user); }

    public User findOneByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public User findOneById(Long id) {

        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public void deleteById(Long id) {
        User user = findOneById(id);
        userRepository.delete(user);
    }

    public List<User> findAllUser () {
        return userRepository.findAll();
    }

//private UserDao userDAO;

//@Autowired
//private RoleServiceImpl roleService;
//
//    @Autowired
//    public UserServiceImpl(UserDao userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    public List<User> getAll() {
//        return userDAO.getAll();
//    }
//
//    public User get(Long id) {
//        return userDAO.get(id);
//    }
//
//    public void save(User user, String roleUser) {
//        Set<Role> role = new HashSet<>();
//        if (roleUser.equals("ADMIN")) {
//            role.add(roleService.getRoleById(1L));
//            user.setRoles(role);
//            userDAO.save(user);
//        } else {
//            role.add(roleService.getRoleById(2L));
//            user.setRoles(role);
//            userDAO.save(user);
//        }
//    }
//
//    public void saveAdmin(User user) {
//        Set<Role> role = new HashSet<>();
//        role.add(roleService.getRoleById(2L));
//        user.setRoles(role);
//        userDAO.save(user);
//    }
//
//    public User getByLogin(String userlogin) {
//      User user = userDAO.getByLogin(userlogin);
//      return user;
//    }
//
//    public void delete(Long id) {
//        userDAO.delete(id);
//    }
//
//    public void edit(User user) {
//        userDAO.update(user);
//    }


}
