package com.example.stdManagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stdManagement.entity.SubjectCourse;
import com.example.stdManagement.service.SubjectCourseService;

@RestController
@RequestMapping("/subject-course/v1")
public class SubjectCourseController {

	@Autowired
	private SubjectCourseService subjectcourseService;

	@PostMapping("/create-subject-course")
	public SubjectCourse createSubjectCourse(@RequestBody SubjectCourse subjectcourse) {

		return this.subjectcourseService.createSubjectCourse(subjectcourse);
		
	}

	@GetMapping("/get-subject-course")
	public Page<SubjectCourse> retrieveSubjectCourse(int offset, int pagesize) {
		return subjectcourseService.retrieveSubjectCourse(offset, pagesize);
	}

	@DeleteMapping("/delete-subject-course/{id}")
	public Map<String, Object> deleteSubjectCourse(@PathVariable Long id) {
		return subjectcourseService.deleteSubjectCourse(id);
	}

	@PutMapping("/update-subject-course/{id}")
	public Map<String, Object> updateSubjectCourse(@PathVariable Long id, SubjectCourse subjectcourse) {
		return this.subjectcourseService.updateSubjectCourse(id, subjectcourse);
	}
}
