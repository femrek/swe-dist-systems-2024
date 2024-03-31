package com.swedist.computer_lab_backend.dto;

import com.swedist.computer_lab_backend.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String department;

    public StudentDTO(Student student) {
        id = student.getId();
        name = student.getName();
        department = student.getDepartment();
    }
}
