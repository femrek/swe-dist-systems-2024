package com.swedist.computer_lab_gradle.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("")
    public String student() {
        return "student/index";
    }
}
