package com.swedist.computer_lab_gradle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"", "/"})
    public String homePage() {
        return "index";
    }
}
