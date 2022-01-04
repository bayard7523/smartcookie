package com.smartcookie.persistence.repository;

import com.smartcookie.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    Course findCourseById(Long id);

    Course findCourseByName(String name);
}
