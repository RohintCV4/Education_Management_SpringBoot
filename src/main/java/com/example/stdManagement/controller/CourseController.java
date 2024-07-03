package com.example.stdManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.stdManagement.entity.Course;
import com.example.stdManagement.service.CourseService;

@RestController
@RequestMapping("/course/v1")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@PostMapping("/create-course")
	public Course createCourse(@RequestBody Course course) {
		return this.courseService.createCourse(course);
	}

	@DeleteMapping("/delete-course/{id}")
	public Map<String, Object> removeCourse(@PathVariable Long id) {

		return courseService.deleteCourse(id);
	}

//	@GetMapping("/{id}")
//	public Optional<Course> retrieveCourse(@PathVariable Long id) {
//		return this.courseService.retrieveCourse(id);
//	}
	
	@GetMapping("/ascdes")
	public List<Course> retrieveCourse(@RequestParam String dem, Direction direction) {
		return this.courseService.retrieveCourse(dem, direction);
	}
	
	@GetMapping("/search")
	public List<Course> searchCourse(
			@RequestParam(required = false) String name, @RequestParam(required = false) Long fees) {
		return courseService.searchCourse(name, fees);
	}

	@PutMapping("/update-course/{id}")
	public Map<String, Object> updateCourse(@PathVariable Long id, @RequestBody Course course) {

		return courseService.updateCourse(id, course);
	}

}
