package com.example.edu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mark")
public class Mark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "mark")
	private Long mark;

	@ManyToOne
	private Student student;

//	@ManyToOne
//	private StudentAn

	@ManyToOne
	private SubjectCourse subjectCourse;

	public Mark() {
	}

	public Mark(Long id, Long mark) {
		this.id = id;
		this.mark = mark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SubjectCourse getSubjectCourse() {
		return subjectCourse;
	}

	public void setSubjectCourse(SubjectCourse subjectCourse) {
		this.subjectCourse = subjectCourse;
	}

}
