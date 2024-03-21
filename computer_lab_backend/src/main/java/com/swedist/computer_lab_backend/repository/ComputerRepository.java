package com.swedist.computer_lab_backend.repository;

import com.swedist.computer_lab_backend.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
