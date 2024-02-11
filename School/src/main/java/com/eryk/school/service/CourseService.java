package com.eryk.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eryk.school.model.Course;
import com.eryk.school.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

	@Autowired
    private CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        super();
    	this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourse(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
