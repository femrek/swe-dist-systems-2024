package com.swedist.computer_lab_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/computer")
public class ComputerController {
    @GetMapping({"/", ""})
    public String showComputers(){
        return "computer/index";
    }
}
