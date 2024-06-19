package com.example.edu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.edu.dto.QuestionDTO;
import com.example.edu.entity.Question;
import com.example.edu.service.QuestionService;

@RestController
@RequestMapping("/question/v1")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@PostMapping("/")
	public Question createQuestion(@RequestBody Question question) {
		return this.questionService.createQuestion(question);
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> removeQuestion(@PathVariable Long id) {

		return questionService.deleteQuestion(id);
	}

//	@GetMapping("/{id}")
//	public Optional<Question> retrieveQuestion(@PathVariable Long id) {
//		return this.questionService.retrieveQuestion(id);
//	}

	@GetMapping("/")
	public List<QuestionDTO> retrieveQuestion() {
		return this.questionService.retrieveQuestion();

	}

	@PutMapping("/{id}")
	public Map<String, Object> updateQuestion(@PathVariable Long id, @RequestBody Question question) {

		return questionService.updateQuestion(id, question);
	}
}
