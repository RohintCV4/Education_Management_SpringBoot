package com.example.edu.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class QuestionDTO {
	private Long id;
	private String Question;
	private String answer;
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getQuestion() {
//		return Question;
//	}
//	public void setQuestion(String question) {
//		Question = question;
//	}
//	
//	


}

