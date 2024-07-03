package com.example.stdManagement.controller;

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

import com.example.stdManagement.entity.School;
import com.example.stdManagement.service.SchoolService;

@RestController
@RequestMapping("/school/v1")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	@PostMapping("/create-school")
	public School createSchool(@RequestBody School school) {
		return this.schoolService.createSchool(school);
	}

	@DeleteMapping("/delete-school/{id}")
	public Map<String, Object> removeSchool(@PathVariable Long id) {

		return schoolService.deleteSchool(id);
	}

	@GetMapping("/get-school/{id}")
	public Map<String, Object> retrieveSchool(@PathVariable Long id) {
		return this.schoolService.retrieveSchool(id);
	}

	@PutMapping("/update-school/{id}")
	public Map<String, Object> updateSchool(@PathVariable Long id, @RequestBody School school) {

		return schoolService.updateSchool(id, school);
	}

	// API
	@GetMapping("https://reqres.in/api/users?page=2")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}

}
