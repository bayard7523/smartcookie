package org.smartcookie.repository;

import org.smartcookie.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    Course findCourseById(Long id);

    Course findCourseByName(String name);
}
