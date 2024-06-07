package com.example.edu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.dto.ResponseDto;
import com.example.edu.entity.Course;
import com.example.edu.entity.Student;
import com.example.edu.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	public Student createStudent(final Student student) {
		return this.studentRepository.save(student);
	}
	
	public Optional<Student> retrieveStudent(Long id){
//		return this.studentRepository.findAll();
		return this.studentRepository.findById(id);
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
	
	public Map<String,Object> deleteStudent(Long id){
		boolean exists = studentRepository.existsById(id);
		Map<String,Object> response=new HashMap<>();
		if(exists) {
			this.studentRepository.deleteById(id);
			response.put("studentId", id);
			return response;
		}
		else {
			response.put("Message","Not found");
			return response;
		}

	}
	
	public Map<String,Object> updateStudent(Long id,Student studentRequest){
		final Map<String,Object> responseMap=new HashMap<>();
    	final Optional <Student> student = studentRepository.findById(id);
    	if(student.isEmpty()) {
    		responseMap.put("Message","studentID Not found");
    	}
    	else {
    		final Student studentResponse= student.get();
    		if(studentRequest.getName()!= null) {
    			studentResponse.setName(studentRequest.getName());
    		}
    		if(studentRequest.getAddress()!=null) {
    			studentResponse.setAddress(studentRequest.getAddress());
    		}    		
    		this.studentRepository.save(studentResponse);
    		responseMap.put("Message","Successfully Updated");    		
    	}
    	return responseMap;
	}
	
}
