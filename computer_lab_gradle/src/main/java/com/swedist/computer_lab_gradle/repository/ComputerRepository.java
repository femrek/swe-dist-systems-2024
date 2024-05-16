package com.swedist.computer_lab_gradle.repository;

import com.swedist.computer_lab_gradle.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
