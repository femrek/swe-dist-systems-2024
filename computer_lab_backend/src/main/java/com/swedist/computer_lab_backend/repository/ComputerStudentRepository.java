package com.swedist.computer_lab_backend.repository;

import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.model.ComputerStudent;
import com.swedist.computer_lab_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ComputerStudentRepository extends JpaRepository<ComputerStudent, Long> {
    public Set<ComputerStudent> findAllByStudent(Student student);
    public Set<ComputerStudent> findAllByComputer(Computer computer);
}
