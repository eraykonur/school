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
@Table(name = "STUDENT")
public class Student implements Serializable {

	private static final long serialVersionUID = -5470037102051081503L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Student First Name may not be blank")
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	@NotBlank(message = "Student Family Name may not be blank")
	@Column(name = "FAMILYNAME", nullable = false)
	private String familyName;

	@NotBlank(message = "Student Date of birth may not be blank")
	@Column(name = "DATEOFBIRTH", nullable = false)
	private String dateOfBirth;

	@NotBlank(message = "Student Email may not be blank")
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Score> scores;

	public Student() {
		super();
	}

	public Student(String firstName, String familyName, String dateOfBirth, String email) {
		super();
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

}