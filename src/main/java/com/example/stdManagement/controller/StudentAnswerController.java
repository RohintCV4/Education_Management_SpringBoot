package com.example.stdManagement.controller;

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

import com.example.stdManagement.entity.StudentAnswer;
import com.example.stdManagement.service.StudentAnswerService;

@RestController
@RequestMapping("/student-answer/v1")
public class StudentAnswerController {
	@Autowired
	private StudentAnswerService studentanswerService;

	
//	public StudentAnswer createStudentAnswer(@RequestBody StudentAnswer studentanswer) {		
//		return this.studentanswerService.createStudentAnswer(studentanswer);
//	}
	@PostMapping("/create-student-answer")
//	@PreAuthorize("hasAnyAuthority('TEACHER') or hasAnyAuthority('STUDENT') or hasAnyAuthority('ADMIN') ")
	public Map<String, Object> createStudentAnswer(@RequestBody StudentAnswer studentAnswer) {
		Map<String, Object> ans = this.studentanswerService.createStudentAnswer(studentAnswer);
		return ans;
	}

	@DeleteMapping("/delete-student-answer/{id}")
	public Map<String, Object> removeStudentAnswer(@PathVariable Long id) {

		return studentanswerService.deleteStudentAnswer(id);
	}

	@GetMapping("/get-student-answer/{id}")
	public Optional<StudentAnswer> retrieveStudentAnswer(@PathVariable Long id) {
		return studentanswerService.retrieveStudentAnswer(id);
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateStudentAnswer(@PathVariable Long id, @RequestBody StudentAnswer studentanswer) {

		return studentanswerService.updateStudentAnswer(id, studentanswer);
	}

}
