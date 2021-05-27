package org.smartcookie.controller.admin;

import org.smartcookie.model.User;
import org.smartcookie.service.CourseService;
import org.smartcookie.service.RoleService;
import org.smartcookie.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final CourseService courseService;

    public UserController(UserService userService, RoleService roleService, CourseService courseService) {
        this.userService = userService;
        this.roleService = roleService;
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/user/users";
    }

//    @GetMapping("/all/paginated")
//    public String showUsersPaginated(Model model,
//                                     @RequestParam("page") Optional<Integer> page,
//                                     @RequestParam("size") Optional<Integer> size) {
//        int currentPage=page.orElse(1);
//        int pageSize=size.orElse(5);
//        Page<User> userPage=userService.findUsersPaginated(PageRequest.of(currentPage-1,pageSize));
//        model.addAttribute("userPage",userPage);
//        int totalPages=userPage.getTotalPages();
//        if(totalPages>0){
//            List<Integer> pageNumbers= IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//        return "admin/user/paginated-users";
//
//    }

    @GetMapping("/form")
    public String showCreateForm(User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
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
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/user/update-user";

    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.toString());
//            user.setId(id);
//            return "redirect:/users/edit/{id}";
        }
        userService.updateUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUserById(id);
        return "redirect:/users/all";
    }
}
