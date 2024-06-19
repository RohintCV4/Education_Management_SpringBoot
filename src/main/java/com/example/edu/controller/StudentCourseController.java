package com.example.edu.controller;

//import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.edu.entity.StudentCourse;
import com.example.edu.service.StudentCourseService;

@RestController
@RequestMapping("/studentcourse/v1")
public class StudentCourseController {
	@Autowired
	private StudentCourseService studentcourseService;
	
	@PostMapping("/")
	public StudentCourse createStudentCourse(@RequestBody StudentCourse studentcourse) {

		return this.studentcourseService.createStudentCourse(studentcourse);
	}
	
	@GetMapping("/{id}")
	public Optional<StudentCourse> retrieveStudentCourse(@PathVariable Long id) {
		return this.studentcourseService.retrieveStudentCourse(id);
	}
	
	@DeleteMapping("/{id}")
	public Map<String,Object> deleteStudentCourse (@PathVariable Long id){
		return studentcourseService.deleteStudentCourse(id);
	}
	
	@PutMapping("/{id}")
	public Map<String,Object> updateStudentCourse(@PathVariable Long id,StudentCourse studentcourse){
		return this.studentcourseService.updateStudentCourse(id,studentcourse);
	}

}
