package com.example.stdManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.stdManagement.dto.StudentDTO;
import com.example.stdManagement.entity.Student;
import com.example.stdManagement.service.JWTService;
import com.example.stdManagement.service.StudentService;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private JWTService jwtService;
	
//	@PutMapping("/update")
//	public Map<String, Object> update(@RequestBody Student student) {
//		return studentService.updateStudent(student);
//	}
	
	 @PutMapping("/update-student")
	    public Map<String, Object> updateStudent(@RequestBody Student student, @RequestHeader("Authorization") String token) {
	        String tokenWithoutBearer = token.replace("Bearer ", "");
	        return studentService.updateStudent(student, tokenWithoutBearer);
	 }

//	@PostMapping("/")
//	public Student createStudent(@RequestBody Student student) {
//
//		return this.studentService.createStudent(student);
//	}

	@GetMapping("/ascdes")
	public List<Student> retrieveStudent(@RequestParam String dem, Direction direction) {
		return this.studentService.retrieveStudent(dem, direction);
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

//	@GetMapping("/search")
//	public List<Student> searchStudent(@RequestParam(required = false) Long id,
//			@RequestParam(required = false) String name, @RequestParam(required = false) String address,
//			@RequestParam(required = false) Long courseId, @RequestParam(required = false) Long schoolId) {
//		return studentService.searchStudent(name, id, address, courseId, schoolId);
//	}
	
	@GetMapping("/student")
	public List<StudentDTO> getStudents(@RequestParam(required = false) String search,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
       return studentService.getStudents(search, page, size, sortField, sortDirection);
    }

	@DeleteMapping("/delete-student/{id}")
	public Map<String, Object> deleteStudent(@PathVariable Long id) {
		return studentService.deleteStudent(id);
	}

//	@PutMapping("/{id}")
//	public Map<String, Object> updateStudent(@PathVariable Long id, Student student) {
//		return this.studentService.updateStudent(id, student);
//	}

}
