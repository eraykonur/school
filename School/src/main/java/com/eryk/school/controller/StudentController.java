package com.eryk.school.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eryk.school.model.Student;
import com.eryk.school.service.IStudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	private IStudentService studentService;

	public StudentController(IStudentService studentService) {
		super();
		this.studentService = studentService;
	}

//	List students
	@GetMapping("/list")
	public String listStudent(Model model) {
		model.addAttribute("students", studentService.getStudentList());
		return "listStudent";
	}

//	add student
	@GetMapping("/new")
	public String addStudent(Model model) {

		// created student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "addStudent";
	}

//	save student
	@PostMapping("/list")
	public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
		studentService.saveStudent(student);
		redirectAttributes.addFlashAttribute("message", "Student is successfully added to system!");
		// return "redirect:/students/list";
		return "redirect:/students/new";
	}

//	update student
	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		Optional<Student> existingStudent = studentService.getStudent(id);
		if (existingStudent.isPresent())
			model.addAttribute("student", existingStudent);
		return "editStudent";
	}

//	update student actual 
	@PostMapping("/update/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		// Get Student details from database
		Optional<Student> existingStudent = studentService.getStudent(id);
		if (existingStudent.isPresent()) {
			Student exstStudent = existingStudent.get();
			exstStudent.setId(student.getId());
			exstStudent.setFirstName(student.getFirstName());
			exstStudent.setFamilyName(student.getFamilyName());
			exstStudent.setDateOfBirth(student.getDateOfBirth());
			exstStudent.setEmail(student.getEmail());

			// save updated student
			studentService.saveStudent(exstStudent);
		}

		return "redirect:/students/list";
	}

//	delete student
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students/list";
	}

	
	public String validateFormFields() {
		
//		public int calculateAge(
//				  LocalDate birthDate,
//				  LocalDate currentDate) {
//				    // validate inputs ...
//				    return Period.between(birthDate, currentDate).getYears();
//				}
	return null;
	}
}
