package com.smartcookie.controller.admin;

import com.smartcookie.domain.service.impl.RoleService;
import com.smartcookie.domain.service.impl.UserService;
import com.smartcookie.persistence.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String showRolesList(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/role/roles";
    }

    @GetMapping("/form")
    public String showCreateRoleForm(Role role) {
        return "admin/role/add-role";
    }

    @PostMapping("/add")
    public String createRole(@Valid Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/role/add-role";
        }
        model.addAttribute("role", role);
        roleService.createRole(role);
        return "redirect:/roles/all";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateRoleForm(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "admin/role/update-role";

    }

    @PostMapping("/role/update/{id}")
    public String updateRole(@PathVariable("id") Long id, @Valid Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            role.setId(id);
            return "admin/role/update-role";
        }
        roleService.createRole(role);
        return "redirect:/roles/all";
    }
}
