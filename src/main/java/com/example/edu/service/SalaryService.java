package com.example.edu.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.entity.Salary;
import com.example.edu.repository.SalaryRepository;

@Service
public class SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;

	public Salary createSalary(final Salary salary) {
		return this.salaryRepository.save(salary);
	}

	public Optional<Salary> retrieveSalary(Long id) {
		return this.salaryRepository.findById(id);
	}

	public Map<String, Object> deleteSalary(Long id) {
		boolean exists = salaryRepository.existsById(id);
		Map<String, Object> response = new HashMap<>();
		if (exists) {
			this.salaryRepository.deleteById(id);
			response.put("salaryId", id);
			return response;
		} else {
			response.put("Message", "Not found");
			return response;
		}

	}

	public Map<String, Object> updateSalary(Long id, Salary salaryRequest) {
		final Map<String, Object> responseMap = new HashMap<>();
		final Optional<Salary> salary = salaryRepository.findById(id);
		if (salary.isEmpty()) {
			responseMap.put("Message", "SalaryID Not found");
		} else {
			final Salary salaryResponse = salary.get();
			if (salaryRequest.getAmount() != null) {
				salaryResponse.setAmount(salaryRequest.getAmount());
			}

			this.salaryRepository.save(salaryResponse);
			responseMap.put("Message", "Successfully Updated");

		}
		return responseMap;
	}
}
