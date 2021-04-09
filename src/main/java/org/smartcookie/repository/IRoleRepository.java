package org.smartcookie.repository;

import org.smartcookie.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);

    Role findRoleByName(String name);
}
