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

import com.example.edu.entity.StudentAnswer;
import com.example.edu.service.StudentAnswerService;

@RestController
@RequestMapping("/studentanswer/v1")
public class StudentAnswerController {
	@Autowired
	private StudentAnswerService studentanswerService;

	@PostMapping("/")
//	public StudentAnswer createStudentAnswer(@RequestBody StudentAnswer studentanswer) {		
//		return this.studentanswerService.createStudentAnswer(studentanswer);
//	}
	public Map<String, Object> createStudentAnswer(@RequestBody StudentAnswer studentAnswer) {
		return this.studentanswerService.createStudentAnswer(studentAnswer);
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> removeStudentAnswer(@PathVariable Long id) {

		return studentanswerService.deleteStudentAnswer(id);
	}

	@GetMapping("/{id}")
	public Optional<StudentAnswer> retrieveStudentAnswer(@PathVariable Long id) {
		return studentanswerService.retrieveStudentAnswer(id);
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateStudentAnswer(@PathVariable Long id, @RequestBody StudentAnswer studentanswer) {

		return studentanswerService.updateStudentAnswer(id, studentanswer);
	}

}
