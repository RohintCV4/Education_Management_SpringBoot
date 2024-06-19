package com.example.edu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "quiz")
	private String quiz;

	@Column(name = "answer")
	private String answer;

	@ManyToOne
	private Course course;

	@ManyToOne
	private SubjectCourse subjectCourse;

	public Question() {
	}

	public Question(Long id, String quiz, String answer) {
		this.id = id;
		this.quiz = quiz;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuiz() {
		return quiz;
	}

	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;

	}

	public SubjectCourse getSubjectCourse() {
		return subjectCourse;
	}

	public void setSubjectCourse(SubjectCourse subjectCourse) {
		this.subjectCourse = subjectCourse;
	}

}
