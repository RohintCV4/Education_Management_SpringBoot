package com.example.stdManagement.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stdManagement.entity.Student;
import com.example.stdManagement.entity.StudentAnswer;
import com.example.stdManagement.entity.SubjectCourse;
import com.example.stdManagement.repository.StudentAnswerRepository;
import com.example.stdManagement.repository.StudentRepository;
import com.example.stdManagement.repository.SubjectCourseRepository;

@Service
public class StudentAnswerService {

	@Autowired
	private StudentAnswerRepository studentanswerRepository;
	@Autowired
	private SubjectCourseRepository subjectCourseRepository;

	@Autowired
	private StudentRepository studentRepository;
//	@Autowired
//	private StudentCourseRepository studentCourseRepository;
//	public StudentAnswer createStudentAnswer(final StudentAnswer studentanswer) {
//		
//		return this.studentanswerRepository.save(studentanswer);
//	}

	public Map<String, Object> createStudentAnswer(final StudentAnswer studentAnswer) {

		// System.out.println("000000000000000000000000000");
		Map<String, Object> response = new HashMap<>();
		Long subId = studentAnswer.getSubjectCourse().getId();
		Long stuId = studentAnswer.getStudent().getId();
		// StudentAnswer
		// subject=studentanswerRepository.findBySubjectCourseId(studentAnswer.getSubjectCourse()).orElse(null);
//	        Long subId=subject.getSubjectCourse().getId();
//	        Long stuId=subject.getStudent().getId();

		SubjectCourse course = subjectCourseRepository.findById(subId).orElse(null);
		Long courseId = course.getCourse().getId();

//	        System.out.println(stuId+" 0000000000000 "+courseId);
		Student stude = studentRepository.findById(stuId).orElse(null);
//	        Long s=;
		if (courseId == stude.getCourse().getId()) {
			System.out.print("success");
			StudentAnswer savedAnswer = this.studentanswerRepository.save(studentAnswer);
			response.put("studentAnswer", savedAnswer);
			response.put("Message", "Student answer saved successfully");
		} else {
			System.out.print("Student Id can't access this question");
			response.put("Message", "Subject course ID not found");
		}
		return response;

//	        Student student=studentCourseRepository.findByStudentIdAndCourseId();

		/*
		 * // Retrieve the subject course information Optional<SubjectCourse>
		 * subjectCourse = subjectCourseRepository.findById(studentAnswer.getId());
		 * 
		 * if (subjectCourse.isPresent()) { // Check if the student is enrolled in this
		 * subject course List<StudentCourse> studentCourses =
		 * studentCourseRepository.findByStudentIdAndCourseId( studentAnswer.getId(),
		 * subjectCourse.get() );
		 * 
		 * 
		 * if (!studentCourses.isEmpty()) { // Save the student answer StudentAnswer
		 * savedAnswer = this.studentanswerRepository.save(studentAnswer);
		 * response.put("studentAnswer", savedAnswer); response.put("Message",
		 * "Student answer saved successfully"); } else { response.put("Message",
		 * "Student ID can't access this question"); } } else { response.put("Message",
		 * "Subject course ID not found"); }
		 */

	}

	public Optional<StudentAnswer> retrieveStudentAnswer(Long id) {
		return this.studentanswerRepository.findById(id);
	}

	public Map<String, Object> deleteStudentAnswer(Long id) {
		boolean exists = studentanswerRepository.existsById(id);
		Map<String, Object> response = new HashMap<>();
		if (exists) {
			this.studentanswerRepository.deleteById(id);
			response.put("studentAnswerId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}

	}

	public Map<String, Object> updateStudentAnswer(Long id, StudentAnswer studentanswerRequest) {
		final Map<String, Object> responseMap = new HashMap<>();
		final Optional<StudentAnswer> studentanswer = studentanswerRepository.findById(id);
		if (studentanswer.isEmpty()) {
			responseMap.put("Message", "studentanswerID Not found");
		} else {
			final StudentAnswer studentanswerResponse = studentanswer.get();
			if (studentanswerRequest.getAnswer() != null) {
				studentanswerResponse.setAnswer(studentanswerRequest.getAnswer());
			}
			this.studentanswerRepository.save(studentanswerResponse);
			responseMap.put("Message", "Successfully Updated");
		}
		return responseMap;
	}
}
