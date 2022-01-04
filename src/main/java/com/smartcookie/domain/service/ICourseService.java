package com.smartcookie.domain.service;

import com.smartcookie.persistence.entity.Course;

import java.util.List;

public interface ICourseService {
    boolean createCourse(Course course);

    List<Course> getAllCourses();
    Course getCourseById(Long id);

    Course getCourseByName(String name);

    boolean deleteCourseById(Long id);
}
