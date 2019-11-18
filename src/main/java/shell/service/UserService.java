package shell.service;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import shell.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findOneByLogin(String login);

    User findOneById(Long id);

    void deleteById(Long Id);

    List<User> findAllUser ();
}
