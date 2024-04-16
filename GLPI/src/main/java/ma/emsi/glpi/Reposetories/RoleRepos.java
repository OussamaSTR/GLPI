package ma.emsi.glpi.Reposetories;

import ma.emsi.glpi.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepos extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
