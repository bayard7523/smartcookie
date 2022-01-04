package com.smartcookie.service;

import com.smartcookie.model.Course;

import java.util.List;

public interface ICourseService {
    boolean createCourse(Course course);

    List<Course> getAllCourses();
    Course getCourseById(Long id);

    Course getCourseByName(String name);

    boolean deleteCourseById(Long id);
}
