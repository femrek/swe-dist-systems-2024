package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.dto.StudentDTO;
import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.model.Student;
import com.swedist.computer_lab_backend.repository.ComputerRepository;
import com.swedist.computer_lab_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentDTO::new).toList();
    }

    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return new StudentDTO(student);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setDepartment(studentDTO.getDepartment());
        Student savedStudent = studentRepository.save(student);
        return new StudentDTO(savedStudent);
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId()).orElseThrow();
        student.setName(studentDTO.getName());
        student.setDepartment(studentDTO.getDepartment());
        Student savedStudent = studentRepository.save(student);
        return new StudentDTO(savedStudent);
    }

    public void deleteComputer(Long id) {
        studentRepository.deleteById(id);
    }
}
