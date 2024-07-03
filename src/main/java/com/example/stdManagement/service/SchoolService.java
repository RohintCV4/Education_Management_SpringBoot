package com.example.stdManagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stdManagement.entity.Course;
import com.example.stdManagement.entity.School;
import com.example.stdManagement.repository.CourseRepository;
import com.example.stdManagement.repository.SchoolRepository;

@Service
public class SchoolService {
	@Autowired
	private SchoolRepository schoolRepository;
	@Autowired
	private CourseRepository courseRepository;

	public School createSchool(final School school) {
		return this.schoolRepository.save(school);
	}

	public Map<String, Object> retrieveSchool(Long id) {
		// Optional<School> school=schoolRepository.findById(id);
		Map<String, Object> schoolMap = new LinkedHashMap<>();
		School sc = schoolRepository.findById(id).orElse(null);
		schoolMap.put("School id:", sc.getId());
		schoolMap.put("School Name:", sc.getName());
//		Course course=courseRepository.findById(id).orElse(null);
		List<Course> course = courseRepository.findAllBySchoolId(id);
//		Iterator<Course> it= course.iterator();
//		while(it.hasNext()) {
//			Course c=it.next();
//			if(sc.getId()==c.getSchool().getId()) {
//				System.out.println("succ");
//				schoolMap.put("Course Name", c.getName());
//				schoolMap.put("Course Id", c.getId());
//				
//			}
//		}

		List<Map<String, Object>> courseData = new ArrayList<>();
		for (Course c : course) {
			Map<String, Object> courseMap = new LinkedHashMap<>();
			courseMap.put("Course Id", c.getId());
			courseMap.put("Course Name", c.getName());
			courseData.add(courseMap);
		}
		schoolMap.put("Courses", courseData);

		return schoolMap;

	}

	public Map<String, Object> deleteSchool(Long id) {

		boolean exists = schoolRepository.existsById(id);

		Map<String, Object> response = new HashMap<>();
//		response.put("Message","Success");
//		return response;
		if (exists) {
			this.schoolRepository.deleteById(id);
			response.put("id", id);
			response.put("message", "Success deleted");
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}

	}

	public Map<String, Object> updateSchool(Long id, School schoolRequest) {
		final Map<String, Object> responseMap = new HashMap<>();
		final Optional<School> school = schoolRepository.findById(id);
		if (school.isEmpty()) {
			responseMap.put("Message", "ID Not found");
		} else {
			final School schoolResponse = school.get();
			if (schoolRequest.getName() != null) {
				schoolResponse.setName(schoolRequest.getName());
			}
			if (schoolRequest.getName() != null) {
				schoolResponse.setName(schoolRequest.getName());
			}
			if (schoolRequest.getAddress() != null) {
				schoolResponse.setAddress(schoolRequest.getAddress());
			}
			this.schoolRepository.save(schoolResponse);
			responseMap.put("Message", "Successfully Updated");

		}
		return responseMap;
	}

}
