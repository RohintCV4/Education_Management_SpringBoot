package com.example.stdManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.stdManagement.dto.TeacherDTO;
import com.example.stdManagement.entity.Teacher;
import com.example.stdManagement.service.JWTService;
import com.example.stdManagement.service.TeacherService;

@RestController
@RequestMapping("/teacher/v1")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private JWTService jwtService;
	
	
	@PutMapping("/update-teacher")
	@PreAuthorize("hasAnyAuthority('TEACHER') or hasAnyAuthority('STUDENT') ")
    public Map<String, Object> updateTeacher(@RequestBody Teacher teacher, @RequestHeader("Authorization") String token) {
        String tokenWithoutBearer = token.replace("Bearer ", "");
        return teacherService.updateTeacher(teacher, tokenWithoutBearer);
        
 }
	
//	@GetMapping("/search-fields")
//	public List<TeacherDTO> getTeacher(@RequestParam(required = false) String search,
//            @RequestParam(required = false) Integer page,
//            @RequestParam(required = false) Integer size,
//            @RequestParam(required = false, defaultValue = "name") String sortField,
//            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
//       return teacherService.getTeacher(search, page, size, sortField, sortDirection);
//    }
	
	
	@GetMapping("/search-fields")
    public List<TeacherDTO> searchTeachers(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortField", defaultValue = "name") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) {
        return teacherService.getTeachers(search, page, size, sortField, sortDirection);
    }

//	@PostMapping("/")
//	public Teacher createTeacher(@RequestBody Teacher teacher) {
//
//		return this.teacherService.createTeacher(teacher);
//	}

	@GetMapping("/get-teacher")
	public List<Map<String, Object>> retrieveTeacher() {
		return this.teacherService.retrieveTeacher();
	}

	@DeleteMapping("/delete-teacher/{id}")
	public Map<String, Object> deleteTeacher(@PathVariable Long id) {
		return teacherService.deleteTeacher(id);
	}
//
//	@PutMapping("/{id}")
//	public Map<String, Object> updateTeacher(@PathVariable Long id, Teacher teacher) {
//		return this.teacherService.updateTeacher(id, teacher);
//	}
}
