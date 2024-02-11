package com.eryk.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eryk.school.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}


