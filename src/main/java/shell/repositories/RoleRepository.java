package shell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shell.model.Role;

import java.util.Set;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByRole(String role);
}
