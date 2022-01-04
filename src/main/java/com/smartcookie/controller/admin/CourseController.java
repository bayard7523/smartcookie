package com.smartcookie.controller.admin;

import com.smartcookie.model.Course;
import com.smartcookie.service.CategoryService;
import com.smartcookie.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("courses")
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String showCoursesList(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/course/courses";
    }

    @GetMapping("/form")
    public String showCreateCourseForm(Course course, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/course/add-course";
    }

    @PostMapping("/add")
    public String create(@Valid Course course, BindingResult result, Model model) {
        if ((result.hasErrors())) {
            return "admin/course/add-course";
        }
        model.addAttribute("course", course);
        courseService.createCourse(course);
        return "redirect:/courses/all";
    }

    @PostMapping("/course/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "admin/course/update-course";
        }
        courseService.createCourse(course);
        return "redirect:/courses/all";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Model model) {
        courseService.deleteCourseById(id);
        return "redirect:/courses/all";
    }

}
