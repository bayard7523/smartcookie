package com.smartcookie.service;

import com.smartcookie.model.Category;
import com.smartcookie.model.Course;
import com.smartcookie.repository.ICategoryRepository;
import com.smartcookie.repository.ICourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService {
    private final ICourseRepository iCourseRepository;
    private final ICategoryRepository iCategoryRepository;

    public CourseService(ICourseRepository iCourseRepository, ICategoryRepository iCategoryRepository) {
        this.iCourseRepository = iCourseRepository;
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public boolean createCourse(Course course) {
        Course courseDb = iCourseRepository.findCourseByName(course.getName());
        if (courseDb != null) {
            return false;
        }
        Course c = new Course();
        c.setName(course.getName());
        c.setPrice(course.getPrice());
        Category courseCategory = iCategoryRepository.findCategoryById(course.getCategory().getId());
        c.setCategory(courseCategory);
        iCourseRepository.save(c);
        return true;
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(iCourseRepository.findAll());
    }

    @Override
    public Course getCourseById(Long id) {
        return iCourseRepository.findCourseById(id);
    }

    @Override
    public Course getCourseByName(String name) {
        return iCourseRepository.findCourseByName(name);
    }

    @Override
    public boolean deleteCourseById(Long id) {
        if (iCourseRepository.findById(id).isPresent()) {
            iCourseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
