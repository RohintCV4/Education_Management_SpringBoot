package com.example.edu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.StudentAnswer;
import com.example.edu.entity.SubjectCourse;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

//	List<StudentAnswer> findAllByStudentIdAndSubjectCourseId(Long studentId, Long subjectcourseId);

//	List<StudentAnswer> findAllByStudentIdAndSubjectCourseId(Long studentId, Long questionId);
//
//	List<StudentAnswer> findAllByStudentId(Long studentId);

	Optional<StudentAnswer> findBySubjectCourseId(SubjectCourse subjectCourse);

//	Optional<StudentAnswer> findByCourseId(SubjectCourse subjectCourse);

}
