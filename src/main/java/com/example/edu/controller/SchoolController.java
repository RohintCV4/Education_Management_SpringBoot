package com.example.edu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.edu.entity.School;
import com.example.edu.service.SchoolService;

@RestController
@RequestMapping("/api/v1")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	@PostMapping("/")
	public School createSchool(@RequestBody School school) {
		return this.schoolService.createSchool(school);
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> removeSchool(@PathVariable Long id) {

		return schoolService.deleteSchool(id);
	}

	@GetMapping("/{id}")
	public Map<String, Object> retrieveSchool(@PathVariable Long id) {
		return this.schoolService.retrieveSchool(id);
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateSchool(@PathVariable Long id, @RequestBody School school) {

		return schoolService.updateSchool(id, school);
	}

	// API
	@GetMapping("https://reqres.in/api/users?page=2")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}

}
