package com.example.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.entity.Course;
//import com.example.edu.entity.Mark;
import com.example.edu.entity.School;
import com.example.edu.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public Course createCourse(final Course course) {
		return this.courseRepository.save(course);
	}
	
	public Optional<Course> retrieveCourse(Long id){
		return this.courseRepository.findById(id);
	}
	
	public Map<String,Object> deleteCourse(Long id){
		boolean exists = courseRepository.existsById(id);
		Map<String,Object> response=new HashMap<>();
		if(exists) {
			this.courseRepository.deleteById(id);
			response.put("courseId", id);
			return response;
		}
		else {
			response.put("Message","Not found");
			return response;
		}

	}
	
	public Map<String,Object> updateCourse(Long id,Course courseRequest){
		final Map<String,Object> responseMap=new HashMap<>();
    	final Optional <Course> course = courseRepository.findById(id);
    	if(course.isEmpty()) {
    		responseMap.put("Message","CourseID Not found");
    	}
    	else {
    		final Course courseResponse= course.get();
    		if(courseRequest.getSchool().getId() != null ) {
    			School school = new School();
    			school.setId(courseRequest.getSchool().getId());
    			courseResponse.setSchool(school);
    		}
    		if(courseRequest.getName() != null) {
    			courseResponse.setName(courseRequest.getName());
    		}
    		if(courseRequest.getFees()!=null) {
    			courseResponse.setFees(courseRequest.getFees());
    		}
    		this.courseRepository.save(courseResponse);
    		responseMap.put("Message","Successfully Updated");    		
    	}
    	return responseMap;
	}
}
