package com.smartcookie.service;

import com.smartcookie.model.Role;
import com.smartcookie.repository.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    private final IRoleRepository iRoleRepository;

    public RoleService(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public void createRole(Role role) {
        iRoleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(iRoleRepository.findAll());
    }

    @Override
    public Role getRoleById(Long id) {
        return iRoleRepository.findRoleById(id);
    }
}
