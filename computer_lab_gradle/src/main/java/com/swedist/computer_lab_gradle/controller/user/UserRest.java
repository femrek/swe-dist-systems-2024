package com.swedist.computer_lab_gradle.controller.user;

import com.swedist.computer_lab_gradle.dto.PasswordUpdateRequest;
import com.swedist.computer_lab_gradle.dto.UserUpdateRequest;
import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRest {
    private final UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                              @PathVariable Long id) {
        UserDTO savedUser = userService.updateUser(userUpdateRequest, id);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<Void> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest,
                                               @PathVariable Long userId) {
        userService.updatePassword(passwordUpdateRequest, userId);
        return ResponseEntity.ok().build();
    }
}
