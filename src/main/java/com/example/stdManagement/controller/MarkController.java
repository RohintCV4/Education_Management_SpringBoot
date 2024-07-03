package com.example.stdManagement.controller;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stdManagement.entity.Mark;
import com.example.stdManagement.service.MarkService;

@RestController
@RequestMapping("/mark/v1")
public class MarkController {

	@Autowired
	private MarkService markservice;

	@PostMapping("/create-mark")
	public Mark createMark(@RequestBody Mark mark) {

		return markservice.createMark(mark);
	}

	@GetMapping("/get-mark")
	public Mark retrieveMark(Long studentId, Long subjectId) throws BadRequestException {
		// System.out.println(studentId+" ---------------------- "+subjectId);
		return markservice.retrieveMark(studentId, subjectId);
	}

	@DeleteMapping("/delete-mark/{id}")
	public Map<String, Object> removeMark(@PathVariable Long id) {

		return markservice.deleteMark(id);
	}
//	@PutMapping("/{id}")
//	public Map<String,Object> updateMark(@PathVariable Long id,@RequestBody Mark mark){
//		
//		return markservice.updateMark(id,mark);
//	}

}
