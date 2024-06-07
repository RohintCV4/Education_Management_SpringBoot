package com.example.edu.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.entity.Mark;
import com.example.edu.entity.Question;
import com.example.edu.entity.Student;
import com.example.edu.entity.StudentAnswer;
import com.example.edu.repository.MarkRepository;
import com.example.edu.repository.QuestionRepository;
import com.example.edu.repository.StudentAnswerRepository;
import com.example.edu.repository.StudentRepository;
import com.example.edu.repository.SubjectCourseRepository;

@Service
public class MarkService {

	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	@Autowired
	private MarkRepository markRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private SubjectCourseRepository subjectCourseRepository;
	@Autowired
	private StudentRepository studentRepository;

	public Mark retrieveMark(Long studentId, Long subjectId) throws BadRequestException {
		Map<Long, Map<Long, Long>> studentMark = new HashMap<>();

		// Get all student answers and questions
		List<StudentAnswer> stdans = studentAnswerRepository.findAll().stream()
				.filter(sa -> sa.getStudent().getId().equals(studentId)
						&& sa.getQuestion().getSubjectCourse().getId().equals(subjectId))
				.sorted(Comparator.comparing(sa -> sa.getQuestion().getId())).collect(Collectors.toList());

		List<Question> questions = questionRepository.findAll().stream()
				.filter(q -> q.getSubjectCourse().getId().equals(subjectId))
				.sorted(Comparator.comparing(Question::getId)).collect(Collectors.toList());
		// Initialize student mark map
		studentMark.put(studentId, new HashMap<>());
//        if(stdans.size() != questions.size()) {
//        	throw new BadRequestException(" Index problem ");
//        }
		for (int i = 0; i < questions.size(); i++) {
			final Question question = questions.get(i);
			final Optional<StudentAnswer> studentAnswers = stdans.stream()
					.filter(s -> Objects.equals(s.getQuestion().getId(), question.getId())).findFirst();
			if (studentAnswers.isPresent()) {
				Map<Long, Long> questionMarks = studentMark.get(studentId);
				Long questionId = studentAnswers.orElseThrow(() -> new BadRequestException("id not found"))
						.getQuestion().getId();
				questionMarks.put(questionId, questionMarks.getOrDefault(questionId, 0L) + 1);
			}
		}

		// Calculate total mark for the student
		Long totalMark = studentMark.get(studentId).values().stream().mapToLong(Long::longValue).sum();

		// Create a new Mark object and save it
		Mark mark = new Mark();
		mark.setStudent(studentRepository.findById(studentId).orElse(null));
		mark.setSubjectCourse(subjectCourseRepository.findById(subjectId).orElse(null));
		mark.setMark(totalMark);

		return markRepository.save(mark);
	}

	public Mark createMark(final Mark mark) {
		return this.markRepository.save(mark);
	}

	public Map<String, Object> deleteMark(Long id) {
		boolean exists = markRepository.existsById(id);
		Map<String, Object> response = new HashMap<>();
		if (exists) {
			this.markRepository.deleteById(id);
			response.put("markId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}
	}
}
