package com.example.edu.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.edu.dto.ResponseDto;
import com.example.edu.entity.Course;
//import com.example.edu.entity.Personal;
import com.example.edu.entity.Student;
import com.example.edu.service.StudentService;

@RestController
@RequestMapping("/student/v1")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/")
	public Student createStudent(@RequestBody Student student) {

		return this.studentService.createStudent(student);
	}
	
	@GetMapping("/")
	public List<Student> retrieveStudent(@RequestParam String dem, Direction direction) {
		return this.studentService.retrieveStudent(dem,direction);
	}
//	@GetMapping("/")
//	public List<Student> retrieveStudent(@RequestParam String dem, Sort sort) {
//		if(dem.equals("1")){
//		sort=Sort.by(Sort.Direction.ASC,"Name");
//		}
//		else {
//		sort=Sort.by(Sort.Direction.DESC,"Name");
//
//		}
//		return this.studentService.retrieveStudent(sort);
//	}
//	@GetMapping("/")
//	public List<ResponseDto> retrieveStudent() {
//		return this.studentService.retrieveStudent();
//	}
	
	@DeleteMapping("/{id}")
	public Map<String,Object> deleteStudent (@PathVariable Long id){
		return studentService.deleteStudent(id);
	}
	
	@PutMapping("/{id}")
	public Map<String,Object> updateStudent(@PathVariable Long id,Student student){
		return this.studentService.updateStudent(id,student);
	}

}
