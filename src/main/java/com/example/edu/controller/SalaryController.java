package com.example.edu.controller;

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

//import com.example.edu.entity.Course;
import com.example.edu.entity.Salary;
//import com.example.edu.service.CourseService;
import com.example.edu.service.SalaryService;

@RestController
@RequestMapping("/salary/v1")
public class SalaryController {
	@Autowired
	private SalaryService salaryService;

	@PostMapping("/")
	public Salary createSalary(@RequestBody Salary salary) {
		return this.salaryService.createSalary(salary);
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> removeSalary(@PathVariable Long id) {

		return salaryService.deleteSalary(id);
	}

	@GetMapping("/{id}")
	public Optional<Salary> retrieveSalary(@PathVariable Long id) {
		return this.salaryService.retrieveSalary(id);
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateSalary(@PathVariable Long id, @RequestBody Salary salary) {

		return salaryService.updateSalary(id, salary);
	}

}
