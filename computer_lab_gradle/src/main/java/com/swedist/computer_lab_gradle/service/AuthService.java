package com.swedist.computer_lab_gradle.service;

import com.swedist.computer_lab_gradle.dto.AuthenticationRequest;
import com.swedist.computer_lab_gradle.dto.AuthenticationResponse;
import com.swedist.computer_lab_gradle.dto.RegisterRequest;
import com.swedist.computer_lab_gradle.entity.AppRole;
import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;
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
                .roles(Set.of(AppRole.USER))
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
}
