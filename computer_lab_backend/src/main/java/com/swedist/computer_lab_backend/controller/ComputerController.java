package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.swedist.computer_lab_backend.constants.AppConstants.SUCCESS_MESSAGE_KEY;
import static com.swedist.computer_lab_backend.constants.AppConstants.ERROR_MESSAGE_KEY;
import static com.swedist.computer_lab_backend.constants.AppConstants.COMPUTER_LIST_KEY;

@Controller
@RequestMapping("/computer")
public class ComputerController {
    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping({"/", ""})
    public String showComputers(Model model) {
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        model.addAttribute(SUCCESS_MESSAGE_KEY, "Computer list fetched successfully");
        return "computer/index";
    }

    @PostMapping({"/", ""})
    public String addComputer(@RequestBody ComputerDTO computerDTO, Model model) {
        try {
            ComputerDTO createdComputer = computerService.createComputer(computerDTO);
            model.addAttribute(
                    SUCCESS_MESSAGE_KEY, "Computer added successfully. id: %d".formatted(createdComputer.getId()));
        } catch (Exception e) {
            model.addAttribute(ERROR_MESSAGE_KEY, "Failed to add computer");
        }
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        return "computer/index";
    }

    @PutMapping({"/", ""})
    public String updateComputer(@RequestBody ComputerDTO computerDTO, Model model) {
        try {
            ComputerDTO updatedComputer = computerService.updateComputer(computerDTO);
            model.addAttribute(
                    SUCCESS_MESSAGE_KEY, "Computer updated successfully. id: %d".formatted(updatedComputer.getId()));
        } catch (Exception e) {
            model.addAttribute(
                    ERROR_MESSAGE_KEY, "Failed to update computer. id: %d".formatted(computerDTO.getId()));
        }
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        return "computer/index";
    }

    @DeleteMapping("/{id}")
    public String deleteComputer(@PathVariable Long id, Model model) {
        computerService.deleteComputer(id);
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        model.addAttribute(SUCCESS_MESSAGE_KEY, "Computer deleted successfully");
        return "computer/index";
    }
}
