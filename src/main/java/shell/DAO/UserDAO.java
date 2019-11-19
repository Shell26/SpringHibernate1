package shell.DAO;

import shell.model.User;

import java.util.List;


public interface UserDAO {

    User get(Long id);
    User getByLogin(String username);
    List<User> getAll();
    void save(User user);
    void update(User user, Long id);
    void delete(Long id);

}
