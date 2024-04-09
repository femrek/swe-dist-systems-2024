package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.service.ComputerService;
import com.swedist.computer_lab_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.swedist.computer_lab_backend.constants.AppConstants.*;

@Controller
@RequestMapping("/computer")
public class ComputerController {
    private final ComputerService computerService;

    private final StudentService studentService;

    @Autowired
    public ComputerController(ComputerService computerService, StudentService studentService) {
        this.computerService = computerService;
        this.studentService = studentService;
    }

    @GetMapping({"/", ""})
    public String showComputers(@RequestParam(required = false) String successMessage,
                                @RequestParam(required = false) String errorMessage,
                                Model model) {
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        model.addAttribute(SUCCESS_MESSAGE_KEY,
                Objects.requireNonNullElse(successMessage, "Computer list fetched successfully"));
        if (errorMessage != null) model.addAttribute(ERROR_MESSAGE_KEY, errorMessage);
        return "computer/index";
    }

    @PostMapping(value = {"/", ""})
    public String addComputer(ComputerDTO computerDTO) {
        String successMessage = null;
        String errorMessage = null;
        try {
            ComputerDTO createdComputer = computerService.createComputer(computerDTO);
            successMessage = "Computer added successfully. id: %d".formatted(createdComputer.getId());
        } catch (Exception e) {
            errorMessage = "Failed to add computer";
        }
        return getRedirect(successMessage, errorMessage);
    }

    @PostMapping("/{id}")
    public String updateComputer(ComputerDTO computerDTO, @PathVariable Long id) {
        String successMessage = null;
        String errorMessage = null;
        try {
            computerDTO.setId(id);
            ComputerDTO updatedComputer = computerService.updateComputer(computerDTO);
            successMessage = "Computer updated successfully. id: %d".formatted(updatedComputer.getId());
        } catch (Exception e) {
            errorMessage = "Failed to update computer. id: %d".formatted(computerDTO.getId());
        }
        return getRedirect(successMessage, errorMessage);
    }

    @PostMapping("/{id}/delete")
    public String deleteComputer(@PathVariable Long id) {
        String successMessage = null;
        String errorMessage = null;
        try {
            computerService.deleteComputer(id);
            successMessage = "Computer deleted successfully. id: %d".formatted(id);
        } catch (Exception e) {
            errorMessage = "Failed to delete computer. id: %d".formatted(id);
        }
        return getRedirect(successMessage, errorMessage);
    }

    private String getRedirect(String successMessage, String errorMessage) {
        StringBuilder redirectUrl = new StringBuilder("redirect:/computer/");
        if (errorMessage != null || successMessage != null) redirectUrl.append("?");
        if (errorMessage != null) redirectUrl.append("errorMessage=").append(errorMessage);
        else if (successMessage != null) redirectUrl.append("successMessage=").append(successMessage);
        return redirectUrl.toString();
    }
}
