package com.eryk.school.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(of = { "id" })
@ToString

@Entity
@Table(name = "SCORE")
public class Score implements Serializable {

	
	private static final long serialVersionUID = 7644415350553830382L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Pattern(regexp = "^[A-F]$", message = "Result Value only A, B, C, D, E, or F are allowed.")
    @NotBlank(message = "Result Value may not be blank")
    @Column(name = "VALUE", nullable = false)
    private String value;

	public Score(Student student, Course course, String value) {
		super();
		this.student = student;
		this.course = course;
		this.value = value;
	}

	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

