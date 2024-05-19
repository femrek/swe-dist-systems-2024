package com.swedist.computer_lab_gradle.controller.user;

import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.dto.request.PasswordUpdateRequest;
import com.swedist.computer_lab_gradle.dto.request.UserUpdateRequest;
import com.swedist.computer_lab_gradle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-administrating")
@RequiredArgsConstructor
public class UserAdministratingRest {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getStudents() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                              @PathVariable Long id) {
        UserDTO savedUser = userService.updateUser(userUpdateRequest, id);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("{}");
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest,
                                                 @PathVariable Long userId) {
        userService.updatePassword(passwordUpdateRequest, userId);
        return ResponseEntity.ok("{}");
    }

    @PostMapping("/{userId}/adminTrue")
    public ResponseEntity<UserDTO> setAdminTrue(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.setAdminTrue(userId));
    }
}
