package com.eryk.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eryk.school.model.Student;
import com.eryk.school.repository.StudentRepository;

@Service
public class StudentService implements IStudentService {

	@Autowired
    private StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        super();
    	this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
