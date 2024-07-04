package com.example.stdManagement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.stdManagement.dto.TeacherDTO;
import com.example.stdManagement.entity.Student;
import com.example.stdManagement.entity.Teacher;
import com.example.stdManagement.repository.StudentRepository;
import com.example.stdManagement.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private JWTService jwtService;

//	public Teacher createTeacher(final Teacher teacher) {
//		return this.teacherRepository.save(teacher);
//	}
	
	
	public Map<String, Object> updateTeacher(Teacher teacherRequest, String token) {
        final Map<String, Object> responseMap = new HashMap<>();

        // Validate the token
        if (!jwtService.isTokenValid(token, teacherRequest)) {
            responseMap.put("Message", "Invalid Email");
            return responseMap;
        }


        String tokenEmail = jwtService.extractUserName(token);


        if (!teacherRequest.getEmail().equals(tokenEmail)) {
            responseMap.put("Message", "Token Email Mismatch");
            return responseMap;
        }


        final Optional<Teacher> teacher = teacherRepository.findByEmail(teacherRequest.getEmail());
        if (teacher.isEmpty()) {
            responseMap.put("Message", "Student Email Not Found");
        } else {


        	final Teacher teacherResponse = teacher.get();
	    if (teacherRequest.getName() != null) {
	        teacherResponse.setName(teacherRequest.getName());
	    }
	    if (teacherRequest.getAddress() != null) {
	        teacherResponse.setAddress(teacherRequest.getAddress());
	    }
	    
	    if (teacherRequest.getSalary() != null && teacherRequest.getSalary() != null) {
	        teacherResponse.setSalary(teacherRequest.getSalary());
	    }
	    if (teacherRequest.getStatus() != null) {
	        teacherResponse.setStatus(teacherRequest.getStatus());
	    }
	    if (teacherRequest.getCourse() != null && teacherRequest.getCourse().getId() != null) {
	        teacherResponse.setCourse(teacherRequest.getCourse());
	    }
	    if (teacherRequest.getSchool() != null && teacherRequest.getSchool().getId() != null) {
	        teacherResponse.setSchool(teacherRequest.getSchool());
	    }


	    System.out.println("Teacher before saving: " + teacher);


	    this.teacherRepository.save(teacherResponse);

	    responseMap.put("Message", "Successfully Updated");
	    
	}
		return responseMap;
	}


	
	
	// public Map <String,Object> retrieveTeacher(){

//		Long teacid= teacher.getId();
//		Long salid=teacher.getSalary().getId();

	public List<Map<String, Object>> retrieveTeacher() {
		List<Teacher> teachers = teacherRepository.findAll();
		return teachers.stream().map(teacher -> {
			Map<String, Object> teacherMap = new LinkedHashMap<>();
			teacherMap.put("Teacher Id", teacher.getId());
			teacherMap.put("Teacher Name", teacher.getName());


				if (teacher.getCourse() != null) {
					teacherMap.put("Course Id", teacher.getCourse().getId());
					teacherMap.put("Course Name", teacher.getCourse().getName());

					List<Student> students = studentRepository.findByCourseId(teacher.getCourse().getId());

					if (!students.isEmpty()) {
						List<Long> studentIds = students.stream().map(Student::getId).collect(Collectors.toList());
						List<String> studentName = students.stream().map(Student::getName).collect(Collectors.toList());
						teacherMap.put("Student IDs", studentIds);
						teacherMap.put("Stduent Name", studentName);
					}
				}
			
			if (teacher.getSchool().getId() != null) {
				teacherMap.put("School id", teacher.getSchool().getId());
				teacherMap.put("School Name", teacher.getSchool().getName());
			}

			return teacherMap;
		}).collect(Collectors.toList());
	}

//		Salary sal=salaryRepository.findById(salaryId).orElse(null);
//		Long courseId=sal.getCourse().getId();
//		Map<String,Object> pri = new HashMap<>();
//		pri.put("Teacher Id : ", teacherId);
//		pri.put("Salary : ", salaryId);
//		pri.put("Course Id : ", courseId);

//		return teacherMap;
//	}
	public Map<String, Object> deleteTeacher(Long id) {
		boolean exists = teacherRepository.existsById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		if (exists) {
			this.teacherRepository.deleteById(id);
			response.put("TeacherId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}
	}
	
	
	

    public List<TeacherDTO> getTeacher(String search, Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10, sort);

        try {
            Page<Object[]> teacherPage = teacherRepository.searchTeachers(search, pageable);
            List<TeacherDTO> teacherDTOs = new ArrayList<>();
            for (Object[] objects : teacherPage) {
                Teacher teacher = (Teacher) objects[0]; // Extract Teacher entity
                String schoolName = (String) objects[1]; // Extract school name
                String courseName = (String) objects[2]; // Extract course name

                TeacherDTO dto = new TeacherDTO();
                dto.setName(teacher.getName());
                dto.setEmail(teacher.getEmail());
                dto.setSalary(teacher.getSalary());
                dto.setAddress(teacher.getAddress());
                dto.setSchoolName(schoolName);
                dto.setCourseName(courseName);

                teacherDTOs.add(dto);
            }
            return teacherDTOs;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception details
            return Collections.emptyList(); // Return an empty list or handle the exception as needed
        }
    }


	
	
	

//	public Map<String, Object> updateTeacher(Long id, Teacher teacherRequest) {
//		final Map<String, Object> responseMap = new HashMap<>();
//		final Optional<Teacher> teacher = teacherRepository.findById(id);
//		if (teacher.isEmpty()) {
//			responseMap.put("Message", "TeacherId not found");
//		} else {
//			final Teacher teacherResponse = teacher.get();
//			if (teacherRequest.getName() != null) {
//				teacherResponse.setName(teacherRequest.getName());
//			}
//			if (teacherRequest.getAddress() != null) {
//				teacherResponse.setAddress(teacherRequest.getAddress());
//			}
//			if (teacherRequest.getStatus() != null) {
//				teacherResponse.setStatus(teacherRequest.getStatus());
//			}
//			this.teacherRepository.save(teacherResponse);
//			responseMap.put("Message", "Successfully Updated");
//		}
//		return responseMap;
//	}

}
