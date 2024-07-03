 package com.example.stdManagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.stdManagement.dto.StudentDTO;
import com.example.stdManagement.entity.Student;
import com.example.stdManagement.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	  @Autowired
	    private JWTService jwtService;

	

	    public Map<String, Object> updateStudent(Student studentRequest, String token) {
	        final Map<String, Object> responseMap = new HashMap<>();

	        // Validate the token
	        if (!jwtService.isTokenValid(token, studentRequest)) {
	            responseMap.put("Message", "Invalid Token");
	            return responseMap;
	        }

	        // Extract the email from the token
	        String tokenEmail = jwtService.extractUserName(token);

	        // Verify the email from the token matches the student's email
	        if (!studentRequest.getEmail().equals(tokenEmail)) {
	            responseMap.put("Message", "Token Email Mismatch");
	            return responseMap;
	        }

	        // Check if the student exists by the email
	        final Optional<Student> student = studentRepository.findByEmail(studentRequest.getEmail());
	        if (student.isEmpty()) {
	            responseMap.put("Message", "Student Email Not Found");
	        } else {
	            final Student studentResponse = student.get();
	            if (studentRequest.getName() != null) {
	                studentResponse.setName(studentRequest.getName());
	            }
	            if (studentRequest.getAddress() != null) {
	                studentResponse.setAddress(studentRequest.getAddress());
	            }
	            if (studentRequest.getCourse() != null && studentRequest.getCourse().getId() != null) {
	                studentResponse.setCourse(studentRequest.getCourse());
	            }
	            if (studentRequest.getSchool() != null && studentRequest.getSchool().getId() != null) {
	                studentResponse.setSchool(studentRequest.getSchool());
	            }
	            this.studentRepository.save(studentResponse);
	            responseMap.put("Message", "Successfully Updated");
	        }
	        return responseMap;
	    }
	
//	public Student createStudent(final Student student) {
//		return this.studentRepository.save(student);
//	}

	public List<Student> retrieveStudent(String dem, Sort.Direction direction) {
		return this.studentRepository.findAll(Sort.by(direction, dem));
	}

//	public List<ResponseDto> retrieveStudent(){
//		List<Student> data = this.studentRepository.findAll();
//		List<ResponseDto> resList = new ArrayList<>();
//		for(Student student:data)
//		{
//			ResponseDto temp = new ResponseDto();
//			temp.setId(student.getId());
//			temp.setAddress(student.getAddress());
//			temp.setName(student.getName());
//			resList.add(temp);
//		}
////		return this.studentRepository.findAll();
//		return resList;
//	}

//	public List<Student> searchStudent(String name, Long id, String address, Long courseId, Long schoolId) {
//		return studentRepository.searchStudents(id, name, address, courseId, schoolId);
//	}

	public Map<String, Object> deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		Map<String, Object> response = new HashMap<>();
		if (exists) {
			this.studentRepository.deleteById(id);
			response.put("studentId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}

	}
//	public String updateStudent(Student student) {
//		Student
//		return null;
//	}

	public List<StudentDTO> getStudents(String search, Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 6, sort);

        
           Page<Student> studentsPage = studentRepository.searchStudents(search, pageable);
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : studentsPage) {
            StudentDTO dto = new StudentDTO();
            dto.setAddress(student.getAddress());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setSchoolName(student.getSchool().getName());
            studentDTOs.add(dto);
        }
        return studentDTOs;
    }

	


	

}
