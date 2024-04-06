package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
    public String showComputers(@RequestParam(required = false) String successMessage, Model model) {
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        model.addAttribute(SUCCESS_MESSAGE_KEY,
                Objects.requireNonNullElse(successMessage, "Computer list fetched successfully"));
        return "computer/index";
    }

    @PostMapping(value = {"/", ""})
    public String addComputer(ComputerDTO computerDTO, Model model) {
        String message;
        try {
            ComputerDTO createdComputer = computerService.createComputer(computerDTO);
            message = "Computer added successfully. id: %d".formatted(createdComputer.getId());
        } catch (Exception e) {
            model.addAttribute(ERROR_MESSAGE_KEY, "Failed to add computer");
            message = "Failed to add computer";
        }
        return "redirect:computer?successMessage=%s".formatted(message);
    }

    @PostMapping("/{id}")
    public String updateComputer(@RequestBody ComputerDTO computerDTO, @PathVariable Long id, Model model) {
        try {
            computerDTO.setId(id);
            ComputerDTO updatedComputer = computerService.updateComputer(computerDTO);
            model.addAttribute(
                    SUCCESS_MESSAGE_KEY, "Computer updated successfully. id: %d".formatted(updatedComputer.getId()));
        } catch (Exception e) {
            model.addAttribute(
                    ERROR_MESSAGE_KEY, "Failed to update computer. id: %d".formatted(computerDTO.getId()));
        }
        return "redirect:computer/";
    }

    @PostMapping("/{id}/delete")
    public String deleteComputer(@PathVariable Long id, Model model) {
        computerService.deleteComputer(id);
        model.addAttribute(SUCCESS_MESSAGE_KEY, "Computer deleted successfully");
        return "redirect:computer/";
    }
}
