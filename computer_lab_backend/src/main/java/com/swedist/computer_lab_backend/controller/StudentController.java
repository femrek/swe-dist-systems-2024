package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.StudentDTO;
import com.swedist.computer_lab_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.swedist.computer_lab_backend.constants.AppConstants.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"/", ""})
    public String showStudents(@RequestParam(required = false) String successMessage,
                               @RequestParam(required = false) String errorMessage,
                               Model model) {
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        model.addAttribute(SUCCESS_MESSAGE_KEY,
                Objects.requireNonNullElse(successMessage, "Student list fetched successfully"));
        if (errorMessage != null) {
            model.addAttribute(ERROR_MESSAGE_KEY, errorMessage);
        }
        return "student/index";
    }

    @PostMapping({"/", ""})
    public String addStudent(@RequestBody StudentDTO studentDTO) {
        String successMessage = null;
        String errorMessage = null;
        try {
            StudentDTO createdStudent = studentService.createStudent(studentDTO);
            successMessage = "Student added successfully. id: %d".formatted(createdStudent.getId());
        } catch (Exception e) {
            errorMessage = "Failed to add student";
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
            successMessage = "Student updated successfully. id: %d".formatted(updateStudent.getId());
        } catch (Exception e) {
            errorMessage = "Failed to update student. id: %d".formatted(studentDTO.getId());
        }
        return getRedirect(successMessage, errorMessage);
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        String successMessage = null;
        String errorMessage = null;
        try {
            studentService.deleteStudent(id);
            successMessage = "Student deleted successfully. id: %d".formatted(id);
        } catch (Exception e) {
            errorMessage = "Failed to delete student. id: %d".formatted(id);
        }
        return getRedirect(successMessage, errorMessage);
    }

    private String getRedirect(String successMessage, String errorMessage) {
        StringBuilder redirectUrl = new StringBuilder("redirect:student/");
        if (errorMessage != null || successMessage != null) redirectUrl.append("?");
        if (errorMessage != null) redirectUrl.append("errorMessage=").append(errorMessage);
        else if (successMessage != null) redirectUrl.append("successMessage=").append(successMessage);
        return redirectUrl.toString();
    }
}
