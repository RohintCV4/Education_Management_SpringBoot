package com.example.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.edu.entity.Personal;
import com.example.edu.entity.School;
//import com.example.edu.entity.Student;
import com.example.edu.repository.SchoolRepository;
@Service
public class SchoolService {
	@Autowired
	private SchoolRepository schoolRepository;
	
	public School createSchool(final School school) {
		return this.schoolRepository.save(school);
	}
	public List<School> retrieveSchool() {
        return this.schoolRepository.findAll();
    }
	
	public Map<String,Object> deleteSchool(Long id){
		
		boolean exists = schoolRepository.existsById(id);
		
		Map<String,Object> response=new HashMap<>();
//		response.put("Message","Success");
//		return response;
		if(exists) {
			this.schoolRepository.deleteById(id);
			response.put("id", id);
			response.put("message","Success deleted");
			return response;
		}
		else {
			response.put("Message","Not found");
			return response;
		}
		
	}
	
	public Map<String,Object> updateSchool(Long id,School schoolRequest){
		final Map<String,Object> responseMap=new HashMap<>();
		final Optional <School> school = schoolRepository.findById(id);
		if(school.isEmpty()) {
			responseMap.put("Message","ID Not found");
		}
		else {
			final School schoolResponse= school.get();
    		if(schoolRequest.getName() != null) {
    			schoolResponse.setName(schoolRequest.getName());
    		}
    		if(schoolRequest.getName() !=null) {
    			schoolResponse.setName(schoolRequest.getName());
    		}
    		if(schoolRequest.getAddress() !=null) {
    			schoolResponse.setAddress(schoolRequest.getAddress());
    		}
    		this.schoolRepository.save(schoolResponse);
    		responseMap.put("Message","Successfully Updated");

		}
		return responseMap;
	}

	

}
