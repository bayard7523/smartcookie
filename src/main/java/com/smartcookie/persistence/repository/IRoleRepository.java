package com.smartcookie.persistence.repository;

import com.smartcookie.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);

    Role findRoleByName(String name);
}
