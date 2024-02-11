package com.eryk.school.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import com.eryk.school.model.Course;
import com.eryk.school.model.Score;
import com.eryk.school.model.Student;
import com.eryk.school.service.ICourseService;
import com.eryk.school.service.IScoreService;
import com.eryk.school.service.IStudentService;

@Controller
@RequestMapping("/scores")
public class ScoreController {

	private IScoreService scoreService;

	private ICourseService courseService;

	private IStudentService studentService;

	public ScoreController(IScoreService scoreService, ICourseService courseService, IStudentService studentService) {
		super();
		this.scoreService = scoreService;
		this.courseService = courseService;
		this.studentService = studentService;
	}

//	List score
	@GetMapping("/list")
	public String listScore(Model model) {
		model.addAttribute("scores", scoreService.getScoreList());
		model.addAttribute("students", studentService.getStudentList());
		model.addAttribute("courses", courseService.getCourseList());
		return "listScore";
	}

//	add score
	@GetMapping("/new")
	public String addScore(Model model) {

		// created score object to hold score form data
		Score score = new Score();
		model.addAttribute("score", score);
		model.addAttribute("students", studentService.getStudentList());
		model.addAttribute("courses", courseService.getCourseList());
		return "addScore";
	}

//	save score
	@PostMapping("/list")
	public String saveScore(@ModelAttribute("score") Score score, @RequestParam("studentId") Long studentId,
			@RequestParam("courseId") Long courseId, @RequestParam("scoreValue") String scoreValue,
			 RedirectAttributes redirectAttributes) {
		
		Optional<Student> student = studentService.getStudent(studentId);
		Optional<Course> course = courseService.getCourse(courseId);
		if (course.isPresent()) {
			Course crs = course.get();
			score.setCourse(crs);
		}
		if (student.isPresent()) {
			Student stdnt = student.get();
			score.setStudent(stdnt);
		}
		if (!StringUtils.isEmptyOrWhitespace(scoreValue)) {
			score.setValue(scoreValue);
		}
		scoreService.saveScore(score);
		
		redirectAttributes.addFlashAttribute("message", "Result is successfully added to system!");

		// return "redirect:/scores/list";
		return "redirect:/scores/new";
	}

//	update score
	@GetMapping("/edit/{id}")
	public String editScore(@PathVariable Long id, Model model) {
		Optional<Score> existingScore = scoreService.getScore(id);
		if (existingScore.isPresent())
			model.addAttribute("score", existingScore);
		return "editScore";
	}

	// update score actual
	@PostMapping("/update/{id}")
	public String updateScore(@PathVariable Long id, @ModelAttribute("score") Score score, Model model) {
		// Get score details from database
		Optional<Score> existingScore = scoreService.getScore(id);
		if (existingScore.isPresent()) {
			Score exstScore = existingScore.get();
			exstScore.setId(score.getId());
			exstScore.setValue(score.getValue());

			// save updated score
			scoreService.saveScore(exstScore);
		}

		return "redirect:/scores/list";
	}

//	delete score
	@GetMapping("/delete/{id}")
	public String deleteScore(@PathVariable Long id) {
		scoreService.deleteScore(id);
		return "redirect:/scores/list";
	}

}
