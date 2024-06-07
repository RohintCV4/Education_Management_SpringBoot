package com.example.edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.StudentAnswer;
import com.example.edu.entity.SubjectCourse;

@Repository
public interface SubjectCourseRepository extends JpaRepository<SubjectCourse, Long> {

	Optional<StudentAnswer> findByCourseId(SubjectCourse subjectCourse);

	Optional<StudentAnswer> findByCourseId(Long subId);

//	Optional<StudentAnswer> findByCourseId(SubjectCourse subjectCourse);

}
