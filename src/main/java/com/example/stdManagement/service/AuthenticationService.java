package com.example.stdManagement.service;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.stdManagement.dto.JwtAuthenticationResponse;
import com.example.stdManagement.dto.RefreshTokenRequest;
import com.example.stdManagement.dto.SignInRequest;
import com.example.stdManagement.dto.SignUpRequest;
import com.example.stdManagement.entity.Admin;
import com.example.stdManagement.entity.Student;
import com.example.stdManagement.entity.Teacher;
import com.example.stdManagement.exceptions.InvalidPasswordException;
import com.example.stdManagement.exceptions.InvalidUsernameException;
import com.example.stdManagement.repository.AdminRepository;
import com.example.stdManagement.repository.SchoolRepository;
import com.example.stdManagement.repository.StudentRepository;
import com.example.stdManagement.repository.TeacherRepository;
import com.example.stdManagement.utils.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public Student studentSignUp(SignUpRequest signUpRequest) {
        Student user = new Student();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.STUDENT);
        return studentRepository.save(user);
    }

    public Teacher teacherSignUp(SignUpRequest signUpRequest) {
        Teacher user = new Teacher();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.TEACHER);
        return teacherRepository.save(user);
    }

    public Admin adminSignUp(SignUpRequest signUpRequest) {
        Admin user = new Admin();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ADMIN);
        return adminRepository.save(user);
    }

    

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        Object user = findUserByEmail(request.getEmail());

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new InvalidPasswordException("Invalid password");
        }

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Object user = findUserByEmail(userEmail);

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            String jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }

    private Object findUserByEmail(String email) {
        if (studentRepository.existsByEmail(email)) {
            return studentRepository.findByEmail(email).orElseThrow(() -> new InvalidUsernameException("Invalid email"));
        } else if (teacherRepository.existsByEmail(email)) {
            return teacherRepository.findByEmail(email).orElseThrow(() -> new InvalidUsernameException("Invalid email"));
        } else if (adminRepository.existsByEmail(email)) {
            return adminRepository.findByEmail(email).orElseThrow(() -> new InvalidUsernameException("Invalid email"));
        } else if (schoolRepository.existsByEmail(email)) {
            return schoolRepository.findByEmail(email).orElseThrow(() -> new InvalidUsernameException("Invalid email"));
        } else {
            throw new InvalidUsernameException("Invalid email");
        }
    }
}
