package com.swedist.computer_lab_gradle.controller.auth;

import com.swedist.computer_lab_gradle.dto.request.AuthenticationRequest;
import com.swedist.computer_lab_gradle.dto.AuthenticationResponse;
import com.swedist.computer_lab_gradle.dto.request.UserCreateRequest;
import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRest {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> user(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(authService.findUserByAuth(authHeader));
    }
}
