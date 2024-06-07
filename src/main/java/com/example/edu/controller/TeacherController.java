package com.example.edu.controller;

import java.util.List;
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

import com.example.edu.entity.Student;
import com.example.edu.entity.Teacher;
import com.example.edu.service.StudentService;
import com.example.edu.service.TeacherService;

@RestController
@RequestMapping("/teacher/v1")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("/")
	public Teacher createTeacher(@RequestBody Teacher teacher) {

		return this.teacherService.createTeacher(teacher);
	}
	
	@GetMapping("/")
	 public List<Map<String, Object>> retrieveTeacher() {
		return this.teacherService.retrieveTeacher();
	}
	
	@DeleteMapping("/{id}")
	public Map<String,Object> deleteTeacher (@PathVariable Long id){
		return teacherService.deleteTeacher(id);
	}
	
	@PutMapping("/{id}")
	public Map<String,Object> updateTeacher(@PathVariable Long id,Teacher teacher){
		return this.teacherService.updateTeacher(id,teacher);
	}
}
