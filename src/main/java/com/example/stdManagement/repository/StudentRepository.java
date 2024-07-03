package com.example.stdManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.stdManagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// Optional<Student> findById(Long id);
//	@Query("SELECT u FROM User ")
//	List<Student> findAll();

	// Student findByCourseId(Long stuId);
	List<Student> findByCourseId(Long courseId);

//
//	
//	@Query(value ="select * from student where name like %:name%")
//	List<Student> findByNameContaining(String name);
//	
//	@Query(value="select * from student where id = :id")
//	List<Student> findByIdContaining(Long id);
//	
//	@Query(value="select * from student where address like %:address%")
//	List<Student> findByAddressContaining(String address);
//	
//	@Query(value="select * from student where courseId = :courseId")
//	List<Student> findByCourseIdContaining(Long courseId);
//	@Query(value = "SELECT * FROM student " + "WHERE (:id IS NULL OR id = :id) "
//			+ "AND (:name IS NULL OR name LIKE %:name%) " + "AND (:address IS NULL OR address LIKE %:address%) "
//			+ "AND (:courseId IS NULL OR course_id = :courseId)"
//			+ "AND (:schoolId IS NULL OR school_id = :schoolId)", nativeQuery = true)
//	List<Student> searchStudents(@Param("id") Long id, @Param("name") String name, @Param("address") String address,
//			@Param("courseId") Long courseId, @Param("schoolId") Long schoolId);
	
	@Query("SELECT s FROM Student s LEFT JOIN s.school school WHERE " +
	           "(:search IS NULL OR s.name LIKE %:search%) OR " +
	           "(:search IS NULL OR s.address LIKE %:search%) OR "+
	           "(:search IS NULL OR s.email LIKE %:search%) OR " +
	           "(:search IS NULL OR s.school.name LIKE %:search%)")
	    Page<Student> searchStudents(@Param("search") String search, Pageable pageable);

	Optional<Student> findByEmail(String email);

	boolean existsByEmail(String email);

//	@Query(value="select * from student where schoolId = :schoolId")
//	List<Student> findBySchoolId(Long schoolId);
}
