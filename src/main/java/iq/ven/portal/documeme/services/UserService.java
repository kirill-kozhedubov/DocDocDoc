package iq.ven.portal.documeme.services;

import iq.ven.portal.documeme.database.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    UserDetails loadUserByUsername(String userName);
}
