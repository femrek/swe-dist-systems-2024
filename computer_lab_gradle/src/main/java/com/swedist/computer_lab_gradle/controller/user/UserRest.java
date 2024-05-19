package com.swedist.computer_lab_gradle.controller.user;

import com.swedist.computer_lab_gradle.dto.request.PasswordUpdateRequest;
import com.swedist.computer_lab_gradle.dto.request.UserUpdateRequest;
import com.swedist.computer_lab_gradle.dto.UserDTO;
import com.swedist.computer_lab_gradle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRest {
    private final UserService userService;

    @PostMapping({"", "/"})
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                              @RequestHeader("Authorization") String authHeader) {
        UserDTO savedUser = userService.updateUser(userUpdateRequest, authHeader);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest,
                                                 @RequestHeader("Authorization") String authHeader) {
        userService.updatePassword(passwordUpdateRequest, authHeader);
        return ResponseEntity.ok("{}");
    }

    @PostMapping("/picture")
    public ResponseEntity<UserDTO> uploadImage(@RequestParam("picture") MultipartFile multipartFile,
                                               @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(userService.setUserImage(multipartFile, authHeader));
    }
}
