package com.smartcookie.persistence.repository;

import com.smartcookie.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);

    Category findCategoryByName(String name);
}
