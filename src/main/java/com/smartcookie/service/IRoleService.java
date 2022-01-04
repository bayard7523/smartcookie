package com.smartcookie.service;

import com.smartcookie.model.Role;

import java.util.List;

public interface IRoleService {
    void createRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

}
