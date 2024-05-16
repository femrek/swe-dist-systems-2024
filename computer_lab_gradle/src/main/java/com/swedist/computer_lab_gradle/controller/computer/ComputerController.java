package com.swedist.computer_lab_gradle.controller.computer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/computer")
public class ComputerController {
    @GetMapping("")
    public String computerPage() {
        return "computer/index";
    }
}
