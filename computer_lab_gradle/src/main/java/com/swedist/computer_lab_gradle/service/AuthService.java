package com.swedist.computer_lab_gradle.service;

import com.swedist.computer_lab_gradle.dto.request.AuthenticationRequest;
import com.swedist.computer_lab_gradle.dto.AuthenticationResponse;
import com.swedist.computer_lab_gradle.dto.request.RegisterRequest;
import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.entity.AppRole;
import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .department(request.getDepartment())
                .roles(Set.of(AppRole.STUDENT))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public UserDTO findUserByAuth(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new UserDTO();
        } else {
            String jwt = authHeader.substring(7);
            String username = jwtService.extractUsername(jwt);
            AppUser user = userRepository.findByUsername(username).orElseThrow();
            return new UserDTO(user);
        }
    }

//    public void updateUser(RegisterRequest request) {
//        // Find the existing user by username
//        AppUser existingUser = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Update the existing user's properties
//        existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
//        existingUser.setFullName(request.getFullName());
//        existingUser.setDepartment(request.getDepartment());
//
//        // Save the updated user
//        userRepository.save(existingUser);
//    }
}
