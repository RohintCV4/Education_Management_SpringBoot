package com.example.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.Course;
import com.example.edu.entity.StudentCourse;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

	// List<StudentCourse> findByStudentIdAndSubjectCourseId(Long id, Long id2);

	List<StudentCourse> findByStudentIdAndCourseId(Long id, Course Course);

}
