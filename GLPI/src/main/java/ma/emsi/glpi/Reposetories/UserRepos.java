package ma.emsi.glpi.Reposetories;

import ma.emsi.glpi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepos extends JpaRepository<User,String> {
    User findByUsername(String userName);

}
