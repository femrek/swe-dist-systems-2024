package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
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

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"/", ""})
    public String showStudents(Model model) {
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        model.addAttribute(SUCCESS_MESSAGE_KEY, "Student list fetched successfully");
        return "student/index";
    }

    @PostMapping({"/", ""})
    public String addStudent(@RequestBody StudentDTO studentDTO, Model model) {
        try {
            StudentDTO createdStudent = studentService.createStudent(studentDTO);
            model.addAttribute(
                    SUCCESS_MESSAGE_KEY, "Computer added successfully. id: %d".formatted(createdStudent.getId()));
        } catch (Exception e) {
            model.addAttribute(ERROR_MESSAGE_KEY, "Failed to add computer");
        }
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        return "student/index";
    }

    @PutMapping({"/", ""})
    public String updateStudent(@RequestBody StudentDTO studentDTO, Model model) {
        try {
            StudentDTO updatedStudent = studentService.updateStudent(studentDTO);
            model.addAttribute(
                    SUCCESS_MESSAGE_KEY, "Computer updated successfully. id: %d".formatted(updatedStudent.getId()));
        } catch (Exception e) {
            model.addAttribute(
                    ERROR_MESSAGE_KEY, "Failed to update computer. id: %d".formatted(studentDTO.getId()));
        }
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        return "student/index";
    }

    @DeleteMapping("/{id}")
    public String deleteComputer(@PathVariable Long id, Model model) {
        studentService.deleteComputer(id);
        model.addAttribute(STUDENT_LIST_KEY, studentService.getStudents());
        model.addAttribute(SUCCESS_MESSAGE_KEY, "Computer deleted successfully");
        return "student/index";
    }
}
