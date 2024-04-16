package ma.emsi.glpi.Services;

import ma.emsi.glpi.Entity.Role;
import ma.emsi.glpi.Entity.User;


public interface UserServ {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);

}
