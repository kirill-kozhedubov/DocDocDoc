package iq.ven.portal.documeme.services.impl;

import iq.ven.portal.documeme.database.user.model.Role;
import iq.ven.portal.documeme.database.user.repository.RoleRepository;
import iq.ven.portal.documeme.database.user.repository.UserRepository;
import iq.ven.portal.documeme.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rolesService")
public class RolesServiceImpl implements RolesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        Role role = roleRepository.findByRole(name);
        return role;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
