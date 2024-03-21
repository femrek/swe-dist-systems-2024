package com.swedist.computer_lab_backend.dto;

import com.swedist.computer_lab_backend.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private Long id;
    private String name;
    private String department;

    public StudentDto(Student student) {
        id = student.getId();
        name = student.getName();
        department = student.getDepartment();
    }
}
