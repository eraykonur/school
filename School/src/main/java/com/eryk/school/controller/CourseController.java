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

import com.eryk.school.model.Course;
import com.eryk.school.service.ICourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	private ICourseService courseService;

	public CourseController(ICourseService courseService) {
		super();
		this.courseService = courseService;
	}

//	List course
	@GetMapping("/list")
	public String listCourse(Model model) {
		model.addAttribute("courses", courseService.getCourseList());
		return "listCourse";
	}

//	add course
	@GetMapping("/new")
	public String addCourse(Model model) {

		// created course object to hold course form data
		Course course = new Course();
		model.addAttribute("course", course);
		return "addCourse";
	}

//	save course
	@PostMapping("/list")
	public String saveCourse(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes) {
		courseService.saveCourse(course);
		redirectAttributes.addFlashAttribute("message", "Course is successfully added to system!");
		//return "redirect:/courses/list";
		return "redirect:/courses/new";
	}

//	update course
	@GetMapping("/edit/{id}")
	public String editCourse(@PathVariable Long id, Model model) {
		Optional<Course> existingCourse = courseService.getCourse(id);
		if (existingCourse.isPresent())
			model.addAttribute("course", existingCourse);
		return "editCourse";
	}

//	update course actual 
	@PostMapping("/update/{id}")
	public String updateCourse(@PathVariable Long id, @ModelAttribute("course") Course course, Model model) {
		// Get Course details from database
		Optional<Course> existingCourse = courseService.getCourse(id);
		if (existingCourse.isPresent()) {
			Course exstCourse = existingCourse.get();
			exstCourse.setId(course.getId());
			exstCourse.setName(course.getName());

			// save updated course
			courseService.saveCourse(exstCourse);
		}

		return "redirect:/courses/list";
	}

//	delete course
	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return "redirect:/courses/list";
	}

}
