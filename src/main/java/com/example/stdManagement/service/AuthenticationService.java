package com.example.stdManagement.service;

import java.util.HashMap;
import java.util.regex.Pattern;

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
import com.example.stdManagement.exceptions.CustomException;
import com.example.stdManagement.exceptions.CustomException.ErrorType;
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
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
		
        if (adminRepository.existsByEmail(signUpRequest.getEmail()) || teacherRepository.existsByEmail(signUpRequest.getEmail()) || studentRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }
        
        

        Student user = new Student();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.STUDENT);
        return studentRepository.save(user);
    }

    public Teacher teacherSignUp(SignUpRequest signUpRequest) {
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
    	
        if (adminRepository.existsByEmail(signUpRequest.getEmail()) || teacherRepository.existsByEmail(signUpRequest.getEmail()) || studentRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }

        Teacher user = new Teacher();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.TEACHER);
        return teacherRepository.save(user);
    }

    public Admin adminSignUp(SignUpRequest signUpRequest) {
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not correct format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
    	
        if (adminRepository.existsByEmail(signUpRequest.getEmail()) || teacherRepository.existsByEmail(signUpRequest.getEmail()) || studentRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }

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
            throw new CustomException("Invalid password", ErrorType.INVALID_PASSWORD);
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
            return studentRepository.findByEmail(email).orElseThrow(() -> new CustomException("Invalid email", ErrorType.INVALID_EMAIL));
        } else if (teacherRepository.existsByEmail(email)) {
            return teacherRepository.findByEmail(email).orElseThrow(() -> new CustomException("Invalid email", ErrorType.INVALID_EMAIL));
        } else if (adminRepository.existsByEmail(email)) {
            return adminRepository.findByEmail(email).orElseThrow(() -> new CustomException("Invalid email", ErrorType.INVALID_EMAIL));
        } else if (schoolRepository.existsByEmail(email)) {
            return schoolRepository.findByEmail(email).orElseThrow(() -> new CustomException("Invalid email", ErrorType.INVALID_EMAIL));
        } else {
            throw new CustomException("Invalid email", ErrorType.INVALID_EMAIL);
        }
    }
    
    public boolean emailValidation(String email) {
		return Pattern.compile("^[a-z0-9+_.-]+@(gmail|yahoo|outlook|zoho)\\.com$").matcher(email).matches();
	}
	private boolean passwordValidation(String password) {
		String pass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		return Pattern.compile(pass).matcher(password).matches();
	}
}
