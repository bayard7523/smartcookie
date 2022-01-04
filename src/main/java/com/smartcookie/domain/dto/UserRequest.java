package com.smartcookie.domain.dto;

import com.smartcookie.persistence.entity.Role;
import com.smartcookie.persistence.entity.User;

import javax.validation.constraints.NotNull;

public class UserRequest extends User {
    @NotNull(message = "Select role")
    private Role role;

    public UserRequest(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
