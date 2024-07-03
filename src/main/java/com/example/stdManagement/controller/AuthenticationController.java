package com.example.stdManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stdManagement.dto.JwtAuthenticationResponse;
import com.example.stdManagement.dto.RefreshTokenRequest;
import com.example.stdManagement.dto.SignInRequest;
import com.example.stdManagement.dto.SignUpRequest;
import com.example.stdManagement.entity.Admin;
import com.example.stdManagement.entity.Student;
import com.example.stdManagement.entity.Teacher;
import com.example.stdManagement.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/student/sign-up")
    public Student studentSignUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationService.studentSignUp(signUpRequest);
    }
    
    @PostMapping("/teacher/sign-up")
    public Teacher teacherSignUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationService.teacherSignUp(signUpRequest);
    }

    @PostMapping("/admin/sign-up")
    public Admin adminSignUp(@RequestBody SignUpRequest signUpRequest){
        return authenticationService.adminSignUp(signUpRequest);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest signInRequest){
        return authenticationService.signIn(signInRequest);
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
    
//    @PutMapping("/{id}")
//	public Map<String, Object> updateStudent(@PathVariable Long id, Student student) {
//		return this.studentService.updateStudent(id, student);
//	}

}
