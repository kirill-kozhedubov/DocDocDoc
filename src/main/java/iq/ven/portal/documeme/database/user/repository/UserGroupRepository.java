package iq.ven.portal.documeme.database.user.repository;

import iq.ven.portal.documeme.database.user.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
    UserGroup findByGroup(String group);
}

