package org.smartcookie.controller.admin;

import org.smartcookie.model.User;
import org.smartcookie.repository.IUserRepository;
import org.smartcookie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/user/users";
    }
    @GetMapping("/form")
    public String showCreateForm(User user) {
        return "admin/user/add-user";
    }

    @PostMapping("/add")
    public String create(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/user/add-user";
        }
        model.addAttribute("user", user);
        userService.createUser(user);
        return "redirect:/users/all";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/update-user";

    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "admin/user/update-user";
        }
        userService.createUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/users/all";
    }
}
