package ma.emsi.glpi.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.glpi.Entity.Role;
import ma.emsi.glpi.Entity.User;
import ma.emsi.glpi.Reposetories.RoleRepos;
import ma.emsi.glpi.Reposetories.UserRepos;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserServImpl implements UserServ {

    private UserRepos userRepos;
    private RoleRepos roleRepos;




    @Override
    public User addNewUser(User user) {
        return userRepos.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepos.save(role);
    }



    @Override
    public User findUserByUserName(String username) {
        return userRepos.findByUsername(username);
    }






    @Override
    public Role findRoleByRoleName(String roleName) {

        return roleRepos.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        if(user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        userRepos.save(user);
        roleRepos.save(role);


    }
}
