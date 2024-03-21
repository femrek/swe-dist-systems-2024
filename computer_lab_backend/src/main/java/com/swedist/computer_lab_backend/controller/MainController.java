package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.ComputerDto;
import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.repository.ComputerRepository;
import com.swedist.computer_lab_backend.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String greet(Model model){
        model.addAttribute("name","asd");
        return "index";
    }

    @PostMapping("/computer")
    public String saveComputer(@RequestBody ComputerDto computerDto, Model model){
        ComputerDto savedComputer = mainService.createComputer(computerDto);
        model.addAttribute("computer",savedComputer);
        return "index";
    }
}
