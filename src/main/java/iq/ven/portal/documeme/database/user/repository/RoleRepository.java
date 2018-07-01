package iq.ven.portal.documeme.database.user.repository;

import iq.ven.portal.documeme.database.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

    Role findById(long id);
}
