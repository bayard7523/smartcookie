package org.smartcookie.controller;

import org.smartcookie.service.CourseService;
import org.smartcookie.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final RoleService roleService;
    private final CourseService courseService;

    public MainController(RoleService roleService, CourseService courseService) {
        this.roleService = roleService;
        this.courseService = courseService;
    }

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/pricing")
    public String pricing(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "pricing";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/teacher")
    public String teacher() {
        return "teacher";
    }

    @GetMapping("/student")
    public String student() {
        return "student";
    }
}
