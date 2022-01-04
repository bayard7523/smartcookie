package com.smartcookie.controller.admin;

import com.smartcookie.domain.service.impl.CategoryService;
import com.smartcookie.domain.service.impl.CourseService;
import com.smartcookie.domain.service.impl.UserService;
import com.smartcookie.persistence.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CourseService courseService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, CourseService courseService, UserService userService) {
        this.categoryService = categoryService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String showCategoriesList(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category/categories";
    }

    @GetMapping("/form")
    public String showCreateCategoryForm(Category category) {
        return "admin/category/add-category";
    }

    @PostMapping("/add")
    public String createCategory(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/category/add-category";
        }
        model.addAttribute("category", category);
        categoryService.createCategory(category);
        return "redirect:/categories/all";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "admin/category/update-category";
    }

    @PostMapping("/category/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "admin/category/update-category";
        }
        categoryService.createCategory(category);
        return "redirect:/categories/all";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        categoryService.deleteCategoryById(id);
        return "redirect:/categories/all";
    }

}
