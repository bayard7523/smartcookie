package com.smartcookie.controller;

import com.smartcookie.domain.service.impl.CustomUserDetails;
import com.smartcookie.domain.service.impl.UserService;
import com.smartcookie.persistence.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showProfile(Model model, Principal principal, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", userDetails);

        return "profile/profile";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", userDetails);
        return "profile/update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUserDetails(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "profile/update-user";
        }
        userService.createUser(user);
        return "redirect:/profile/";
    }
}
