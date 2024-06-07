package com.example.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
