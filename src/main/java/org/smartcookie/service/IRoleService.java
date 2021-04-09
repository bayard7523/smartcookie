package org.smartcookie.service;

import org.smartcookie.model.Role;

import java.util.List;

public interface IRoleService {
    void createRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

}
