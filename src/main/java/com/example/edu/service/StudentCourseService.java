package com.example.edu.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.entity.StudentCourse;
import com.example.edu.repository.StudentCourseRepository;

@Service
public class StudentCourseService {

	@Autowired
	private StudentCourseRepository studentcourseRepository;

	public StudentCourse createStudentCourse(final StudentCourse studentcourse) {
		return this.studentcourseRepository.save(studentcourse);
	}

	public Optional<StudentCourse> retrieveStudentCourse(Long id) {
		return this.studentcourseRepository.findById(id);
	}

	public Map<String, Object> deleteStudentCourse(Long id) {
		boolean exists = studentcourseRepository.existsById(id);
		Map<String, Object> response = new HashMap<>();
		if (exists) {
			this.studentcourseRepository.deleteById(id);
			response.put("studentCourseId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}

	}

	public Map<String, Object> updateStudentCourse(Long id, StudentCourse studentcourseRequest) {
		final Map<String, Object> responseMap = new HashMap<>();
		final Optional<StudentCourse> studentcourse = studentcourseRepository.findById(id);
		if (studentcourse.isEmpty()) {
			responseMap.put("Message", "studentcourseID Not found");
		}
		return responseMap;
	}
}
