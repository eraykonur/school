package com.eryk.school.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(of = { "id" })
@ToString

@Entity
@Table(name = "COURSE")
public class Course implements Serializable {

	private static final long serialVersionUID = -4053820443210971537L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Course Name may not be blank")
	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Score> scores;


	public Course(String name, List<Score> scores) {
		super();
		this.name = name;
		this.scores = scores;
	}

	public Course() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
}
