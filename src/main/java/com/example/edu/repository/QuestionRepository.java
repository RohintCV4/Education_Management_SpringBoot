package com.example.edu.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.Question;
import com.example.edu.entity.StudentAnswer;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findAllByIdIn(List<Long> questionIds);
	
	List<Question> findAllByIdAndAnswer(Long id,String answer);
	
}
