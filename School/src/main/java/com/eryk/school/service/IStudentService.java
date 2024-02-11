package com.eryk.school.service;

import java.util.List;
import java.util.Optional;

import com.eryk.school.model.Student;

public interface IStudentService {
    List<Student> getStudentList();
    Optional<Student> getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
}
