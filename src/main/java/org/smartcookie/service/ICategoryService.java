package org.smartcookie.service;

import org.smartcookie.model.Category;

import java.util.List;

public interface ICategoryService {
    boolean createCategory(Category category);

    List<Category> getAllCategories();
    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    boolean deleteCategoryById(Long id);
}
