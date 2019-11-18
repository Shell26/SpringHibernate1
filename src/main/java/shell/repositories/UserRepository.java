package shell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shell.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);

    @Modifying
    @Query("update User u set u.login = ?1, u.name = ?2, u.password = ?3 where u.id = ?3")
    void edit(String login, String name, String password, Long userId);
}
