package com.smartcookie.domain.service.impl;

import com.smartcookie.domain.service.IRoleService;
import com.smartcookie.persistence.entity.Role;
import com.smartcookie.persistence.repository.IRoleRepository;
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
