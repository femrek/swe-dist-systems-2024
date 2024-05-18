package com.swedist.computer_lab_gradle.controller.picture;

import com.swedist.computer_lab_gradle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureRest {
    private final UserService userService;

    @GetMapping("/{pictureName}")
    public byte[] getProfilePicture(@PathVariable String pictureName) {
        return userService.getPicture(pictureName);
    }
}
