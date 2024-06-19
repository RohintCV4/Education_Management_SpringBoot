package com.example.edu.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.edu.controller.ArrayList;
//import com.example.edu.controller.ResponseDto;
//import com.example.edu.controller.Student;
import com.example.edu.dto.QuestionDTO;
import com.example.edu.entity.Question;
import com.example.edu.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public Question createQuestion(final Question question) {
		return this.questionRepository.save(question);
	}

//	public Optional<Question> retrieveQuestion(Long id){
//		return this.questionRepository.findById(id);
//	}

	public List<QuestionDTO> retrieveQuestion() {
		List<Question> data = this.questionRepository.findAll();
		List<QuestionDTO> resList = new LinkedList<>();
		for (Question quiz : data) {
//			QuestionDTO temp = new QuestionDTO();
//			temp.setId(quiz.getId());
//			temp.setQuestion(quiz.getQuiz());
//			resList.add(temp);
			resList.add(
					QuestionDTO.builder().id(quiz.getId()).Question(quiz.getQuiz()).answer(quiz.getAnswer()).build());
		}
//		return this.studentRepository.findAll();
		return resList;
	}

	public Map<String, Object> deleteQuestion(Long id) {
		boolean exists = questionRepository.existsById(id);
		Map<String, Object> response = new HashMap<>();
		if (exists) {
			this.questionRepository.deleteById(id);
			response.put("questionId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}

	}

	public Map<String, Object> updateQuestion(Long id, Question questionRequest) {
		final Map<String, Object> responseMap = new HashMap<>();
		final Optional<Question> question = questionRepository.findById(id);
		if (question.isEmpty()) {
			responseMap.put("Message", "QuestionID Not found");
		} else {
			final Question questionResponse = question.get();
			if (questionRequest.getQuiz() != null) {
				questionResponse.setQuiz(questionRequest.getQuiz());
			}
			if (questionRequest.getAnswer() != null) {
				questionResponse.setAnswer(questionRequest.getAnswer());
			}

			this.questionRepository.save(questionResponse);
			responseMap.put("Message", "Successfully Updated");

		}
		return responseMap;
	}

}
