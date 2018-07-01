package iq.ven.portal.documeme.services;

import iq.ven.portal.documeme.database.user.model.Role;
import iq.ven.portal.documeme.database.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface RolesService {

    Role findRoleByName(String name);

    void saveRole(Role role);

}
