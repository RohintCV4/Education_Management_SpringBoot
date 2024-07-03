package com.example.stdManagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.stdManagement.repository.AdminRepository;
import com.example.stdManagement.repository.StudentRepository;
import com.example.stdManagement.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = studentRepository.findByEmail(username)
                .orElse(null);
        if (userDetails == null) {
            userDetails = teacherRepository.findByEmail(username)
                    .orElse(null);
        }
        if (userDetails == null) {
            userDetails = adminRepository.findByEmail(username)
                    .orElse(null);
        }
        
        if (userDetails == null) {
            throw new UsernameNotFoundException("User Not Found !!!");
        }
        return userDetails;
    }
}
