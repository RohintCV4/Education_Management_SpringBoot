package com.example.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.example.edu.entity.School;
import com.example.edu.entity.StudentCourse;
import com.example.edu.entity.SubjectCourse;
import com.example.edu.repository.StudentCourseRepository;
import com.example.edu.repository.SubjectCourseRepository;

@Service
public class SubjectCourseService {

	@Autowired
	private SubjectCourseRepository subjectcourseRepository;
	public SubjectCourse createSubjectCourse(final SubjectCourse subjectcourse) {
		return this.subjectcourseRepository.save(subjectcourse);
	}
	
	public Page <SubjectCourse> retrieveSubjectCourse(int offset, int pagesize){
		Page<SubjectCourse> response=subjectcourseRepository.findAll(PageRequest.of(offset,pagesize));
		return response;
		//return this.subjectcourseRepository.findAll();
	}
	
	public Map<String,Object> deleteSubjectCourse(Long id){
		boolean exists = subjectcourseRepository.existsById(id);
		Map<String,Object> response=new HashMap<>();
		if(exists) {
			this.subjectcourseRepository.deleteById(id);
			response.put("SubjectCourseId", id);
			return response;
		}
		else {
			response.put("Message","Not found");
			return response;
		}

	}
	
	public Map<String,Object> updateSubjectCourse(Long id,SubjectCourse subjectcourseRequest){
		final Map<String,Object> responseMap=new HashMap<>();
    	final Optional <SubjectCourse> subjectcourse= subjectcourseRepository.findById(id);
    	if(subjectcourse.isEmpty()) {
    		responseMap.put("Message","subjectcourseID Not found");
    	}
    	else {
    		final SubjectCourse subjectcourseResponse= subjectcourse.get();
    		if(subjectcourseRequest.getName() != null) {
    			subjectcourseResponse.setName(subjectcourseRequest.getName());
    		}
    		
    		this.subjectcourseRepository.save(subjectcourseResponse);
    		responseMap.put("Message","Successfully Updated");
    	}
    	return responseMap;
	}
}
