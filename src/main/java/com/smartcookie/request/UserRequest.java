package com.smartcookie.request;

import com.smartcookie.model.Role;
import com.smartcookie.model.User;

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
