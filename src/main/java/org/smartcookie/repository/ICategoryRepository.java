package org.smartcookie.repository;

import org.smartcookie.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);

    Category findCategoryByName(String name);
}
