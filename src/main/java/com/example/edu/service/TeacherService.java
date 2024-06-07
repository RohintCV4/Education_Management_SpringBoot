package com.example.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.entity.Salary;
import com.example.edu.entity.Teacher;
import com.example.edu.repository.SalaryRepository;
import com.example.edu.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SalaryRepository salaryRepository;
	public Teacher createTeacher(final Teacher teacher) {
		return this.teacherRepository.save(teacher);
	}
	//public Map <String,Object> retrieveTeacher(){
		
//		Long teacid= teacher.getId();
//		Long salid=teacher.getSalary().getId();
		
		  public List<Map<String, Object>> retrieveTeacher() {
		        List<Teacher> teachers = teacherRepository.findAll();
		        return teachers.stream().map(teacher -> {
		            Map<String, Object> teacherMap = new HashMap<>();
		            teacherMap.put("Teacher Id", teacher.getId());
		            teacherMap.put("Teacher Name", teacher.getName());

		            if (teacher.getSalary() != null) {
		                teacherMap.put("Salary Id", teacher.getSalary().getId());
		                teacherMap.put("Salary Amount", teacher.getSalary().getAmount());

		                if (teacher.getSalary().getCourse() != null) {
		                    teacherMap.put("Course Id", teacher.getSalary().getCourse().getId());
		                    teacherMap.put("Course Name", teacher.getSalary().getCourse().getName());
		                }
		            }

		            return teacherMap;
		        } ).collect(Collectors.toList());
		  }
	
		
		
//		Salary sal=salaryRepository.findById(salaryId).orElse(null);
//		Long courseId=sal.getCourse().getId();
//		Map<String,Object> pri = new HashMap<>();
//		pri.put("Teacher Id : ", teacherId);
//		pri.put("Salary : ", salaryId);
//		pri.put("Course Id : ", courseId);
		
		
//		return teacherMap;
//	}
	public Map<String,Object> deleteTeacher(Long id){
		boolean exists = teacherRepository.existsById(id);
		Map<String,Object> response =new HashMap();
		if(exists) {
			this.teacherRepository.deleteById(id);
			response.put("TeacherId", id);
			return response;
		}
		else {
			response.put("Message","Not found");
			return response;
		}
	}
	public Map<String,Object> updateTeacher(Long id,Teacher teacherRequest){
		final Map<String,Object> responseMap =new HashMap<>();
		final Optional<Teacher> teacher=teacherRepository.findById(id);
		if(teacher.isEmpty()) {
			responseMap.put("Message","TeacherId not found");
		}
		else {
			final Teacher teacherResponse=teacher.get();
			if(teacherRequest.getName()!= null) {
				teacherResponse.setName(teacherRequest.getName());
    		}
    		if(teacherRequest.getAddress()!=null) {
    			teacherResponse.setAddress(teacherRequest.getAddress());
    		}    
    		if(teacherRequest.getStatus()!=null) {
    			teacherResponse.setStatus(teacherRequest.getStatus());
    		}   
    		this.teacherRepository.save(teacherResponse);
    		responseMap.put("Message","Successfully Updated");    		
    	}
    	return responseMap;
			}
		
		
	}
	

