package com.example.edu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findById(Long id);
//	@Query("SELECT u FROM User ")
//	List<Student> findAll();

	//Student findByCourseId(Long stuId);
    List<Student> findByCourseId(Long courseId);

	
//	@Query("SELECT u FROM User u WHERE u.status = 1")
//	Collection<User> findAllActiveUsers();
}
