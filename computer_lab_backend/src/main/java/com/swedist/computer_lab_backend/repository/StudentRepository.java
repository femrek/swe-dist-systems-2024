package com.swedist.computer_lab_backend.repository;

import com.swedist.computer_lab_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
