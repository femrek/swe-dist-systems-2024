package com.swedist.computer_lab_gradle.controller.student;

import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.dto.request.UserUpdateRequest;
import com.swedist.computer_lab_gradle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentRest {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getStudents() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(userUpdateRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("{}");
    }
}
