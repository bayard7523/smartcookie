package com.smartcookie.domain.service;

import com.smartcookie.persistence.entity.Role;

import java.util.List;

public interface IRoleService {
    void createRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

}
