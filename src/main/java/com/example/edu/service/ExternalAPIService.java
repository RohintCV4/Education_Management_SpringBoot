package com.example.edu.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalAPIService {
	private final RestTemplate restTemplate = new RestTemplate();

    

    public String getUsers() {
        String url = "https://reqres.in/api/users?page=2";
        return restTemplate.getForObject(url, String.class);
    }
}