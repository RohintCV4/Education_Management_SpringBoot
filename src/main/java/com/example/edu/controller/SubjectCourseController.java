package com.example.edu.controller;

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

import com.example.edu.entity.SubjectCourse;
import com.example.edu.service.SubjectCourseService;

@RestController
@RequestMapping("/subjectcourse/v1")
public class SubjectCourseController {

	@Autowired
	private SubjectCourseService subjectcourseService;

	@PostMapping("/")
	public SubjectCourse createSubjectCourse(@RequestBody SubjectCourse subjectcourse) {

		return this.subjectcourseService.createSubjectCourse(subjectcourse);
	}

	@GetMapping("/")
	public Page<SubjectCourse> retrieveSubjectCourse(int offset, int pagesize) {
		return subjectcourseService.retrieveSubjectCourse(offset, pagesize);
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deleteSubjectCourse(@PathVariable Long id) {
		return subjectcourseService.deleteSubjectCourse(id);
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateSubjectCourse(@PathVariable Long id, SubjectCourse subjectcourse) {
		return this.subjectcourseService.updateSubjectCourse(id, subjectcourse);
	}
}
