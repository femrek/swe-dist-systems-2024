package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.StudentDTO;
import com.swedist.computer_lab_backend.service.ComputerService;
import com.swedist.computer_lab_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.swedist.computer_lab_backend.constants.AppConstants.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final ComputerService computerService;

    @Autowired
    public StudentController(StudentService studentService, ComputerService computerService) {
        this.studentService = studentService;
        this.computerService = computerService;
    }

    @GetMapping({"/", ""})
    public String showStudents(@RequestParam(required = false) String successMessage,
                               @RequestParam(required = false) String errorMessage,
                               Model model) {
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        model.addAttribute(COMPUTER_LIST_KEY, computerService.getComputers());
        if (successMessage != null) model.addAttribute(SUCCESS_MESSAGE_KEY, successMessage);
        if (errorMessage != null) model.addAttribute(ERROR_MESSAGE_KEY, errorMessage);
        return "student/index";
    }

    @PostMapping({"/", ""})
    public String addStudent(StudentDTO studentDTO) {
        String successMessage = null;
        String errorMessage = null;
        try {
            StudentDTO createdStudent = studentService.createStudent(studentDTO);
            successMessage = "Student added successfully: %s".formatted(createdStudent.toVisualString());
        } catch (Exception e) {
            errorMessage = "Failed to add student: %s".formatted(e.getMessage());
        }
        return getRedirect(successMessage, errorMessage);
    }

    @PostMapping("/{id}")
    public String updateStudent(StudentDTO studentDTO, @PathVariable Long id) {
        String successMessage = null;
        String errorMessage = null;
        try {
            studentDTO.setId(id);
            StudentDTO updateStudent = studentService.updateStudent(studentDTO);
            successMessage = "Student updated successfully: %s".formatted(updateStudent.toVisualString());
        } catch (Exception e) {
            errorMessage = "Failed to update student: %s".formatted(e.getMessage());
        }
        return getRedirect(successMessage, errorMessage);
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        String successMessage = null;
        String errorMessage = null;
        try {
            StudentDTO deletedStudent = studentService.deleteStudent(id);
            successMessage = "Student deleted successfully: %s".formatted(deletedStudent.toVisualString());
        } catch (Exception e) {
            errorMessage = "Failed to delete student: %s".formatted(e.getMessage());
        }
        return getRedirect(successMessage, errorMessage);
    }

    private String getRedirect(String successMessage, String errorMessage) {
        StringBuilder redirectUrl = new StringBuilder("redirect:/student/");
        if (errorMessage != null || successMessage != null) redirectUrl.append("?");
        if (errorMessage != null) redirectUrl.append("errorMessage=").append(errorMessage);
        else if (successMessage != null) redirectUrl.append("successMessage=").append(successMessage);
        return redirectUrl.toString();
    }
}
