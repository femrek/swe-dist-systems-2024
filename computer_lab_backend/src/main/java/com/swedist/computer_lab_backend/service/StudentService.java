package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.StudentDTO;
import com.swedist.computer_lab_backend.model.ComputerStudent;
import com.swedist.computer_lab_backend.model.Student;
import com.swedist.computer_lab_backend.repository.ComputerStudentRepository;
import com.swedist.computer_lab_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ComputerStudentRepository computerStudentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          ComputerStudentRepository computerStudentRepository) {
        this.studentRepository = studentRepository;
        this.computerStudentRepository = computerStudentRepository;
    }

    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentDTO::new).toList();
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        String validationError = studentDTO.validate();
        if (validationError != null){
            throw new IllegalArgumentException(validationError);
        }

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setDepartment(studentDTO.getDepartment());
        Student savedStudent = studentRepository.save(student);
        return new StudentDTO(savedStudent);
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        String validationError = studentDTO.validate();
        if (validationError != null){
            throw new IllegalArgumentException(validationError);
        }

        Student student = studentRepository.findById(studentDTO.getId()).orElseThrow(
                () -> new IllegalArgumentException("Student couldn't found with given id")
        );
        student.setName(studentDTO.getName());
        student.setDepartment(studentDTO.getDepartment());
        Student savedStudent = studentRepository.save(student);
        return new StudentDTO(savedStudent);
    }

    public StudentDTO deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student couldn't found with given id")
        );
        Set<ComputerStudent> tiedReservations
                = computerStudentRepository.findAllByStudent(student);
        if (!tiedReservations.isEmpty()) {
            throw new RuntimeException("The student cannot be deleted because the student has already reservations");
        }
        studentRepository.deleteById(id);
        return new StudentDTO(student);
    }
}
