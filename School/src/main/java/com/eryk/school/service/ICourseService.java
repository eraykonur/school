package com.eryk.school.service;

import com.eryk.school.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> getCourseList();
    Optional<Course> getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
}
