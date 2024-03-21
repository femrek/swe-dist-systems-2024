package com.swedist.computer_lab_backend.repository;

import com.swedist.computer_lab_backend.model.ComputerStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerStudentRepository extends JpaRepository<ComputerStudent, Long> {
}
