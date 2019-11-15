package shell.Dao;

import shell.model.User;

import java.util.List;


public interface UserDao {

    User get(Long id);
    User getByLogin(String username);
    List<User> getAll();
    void save(User user);
    void update(User user);
    void delete(Long id);

}
