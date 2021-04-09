package org.smartcookie.controller;

import org.smartcookie.model.User;
import org.smartcookie.service.CustomUserDetails;
import org.smartcookie.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
