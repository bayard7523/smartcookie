package com.smartcookie.domain.service.impl;

import com.smartcookie.domain.service.ICategoryService;
import com.smartcookie.persistence.entity.Category;
import com.smartcookie.persistence.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public boolean createCategory(Category category) {
        Category categoryDb = iCategoryRepository.findCategoryByName(category.getName());
        if (categoryDb != null) {
            return false;
        }
        Category c = new Category();
        c.setName(category.getName());
        iCategoryRepository.save(c);
        return true;
    }

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList<>(iCategoryRepository.findAll());
    }

    @Override
    public Category getCategoryById(Long id) {
        return iCategoryRepository.findCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return iCategoryRepository.findCategoryByName(name);
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        if (iCategoryRepository.findById(id).isPresent()) {
            iCategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
