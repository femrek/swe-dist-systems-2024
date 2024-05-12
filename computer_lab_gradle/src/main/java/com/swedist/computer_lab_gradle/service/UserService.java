package com.swedist.computer_lab_gradle.service;

import com.swedist.computer_lab_gradle.dto.PasswordUpdateRequest;
import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.dto.UserUpdateRequest;
import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserDTO updateUser(UserUpdateRequest userUpdateRequest, Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); //todo handle exception
        if (userUpdateRequest.getFullName() != null) {
            user.setFullName(userUpdateRequest.getFullName());
        }
        if (userUpdateRequest.getDepartment() != null) {
            user.setDepartment(userUpdateRequest.getDepartment());
        }
        return new UserDTO(userRepository.save(user));
    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest, Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); //todo handle exception
        if (!passwordEncoder.matches(passwordUpdateRequest.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect"); //todo handle exception
        }
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }
}
