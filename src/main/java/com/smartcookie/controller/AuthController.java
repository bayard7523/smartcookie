package com.smartcookie.controller;

import com.smartcookie.domain.service.impl.RoleService;
import com.smartcookie.domain.service.impl.UserService;
import com.smartcookie.persistence.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;

    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordError", "Check your password");
            return "auth/registration";
        }
        if (!userService.createUser(user)) {
            model.addAttribute("usernameError", "User with this email exists!");
            return "auth/registration";
        }
        return "redirect:/";
    }


}
