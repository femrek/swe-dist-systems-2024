package com.swedist.computer_lab_backend.dto;

import com.swedist.computer_lab_backend.model.Student;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private Long id;
    private String name;
    private String department;

    public StudentDTO(Student student) {
        id = student.getId();
        name = student.getName();
        department = student.getDepartment();
    }

    public String validate() {
        if (name == null || name.isBlank()) {
            return "Name must be given";
        }
        if (department == null || department.isBlank()) {
            return "Department must be given";
        }

        return null;
    }

    public String toVisualString() {
        return new StringBuilder()
                .append(name)
                .append(", ")
                .append(department)
                .toString();
    }
}
