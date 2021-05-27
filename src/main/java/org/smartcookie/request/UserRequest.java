package org.smartcookie.request;

import org.smartcookie.model.Role;
import org.smartcookie.model.User;

import javax.validation.constraints.NotBlank;
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
