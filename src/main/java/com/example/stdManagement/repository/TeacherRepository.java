package com.example.stdManagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.stdManagement.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	
	@Query("SELECT t, s.name, sal.amount, c.name FROM Teacher t " +
		       "LEFT JOIN t.school s " +
		       "LEFT JOIN t.salary sal " +
		       "LEFT JOIN sal.course c " +
		       "WHERE (:search IS NULL OR t.name LIKE %:search%) OR " +
		       "(:search IS NULL OR t.address LIKE %:search%) OR " +
		       "(:search IS NULL OR t.email LIKE %:search%) OR " +
		       "(:search IS NULL OR s.name LIKE %:search%) OR " +

		       "(:search IS NULL OR c.name LIKE %:search%)")
		Page<Object[]> searchTeachers(@Param("search") String search, Pageable pageable);

	
//	@Query("SELECT t FROM Teacher t WHERE t.name LIKE %:name%")
//    Page<Teacher> searchTeachers(@Param("name") String name, Pageable pageable);
	Optional<Teacher> findByEmail(String email);

	boolean existsByEmail(String email);
}
