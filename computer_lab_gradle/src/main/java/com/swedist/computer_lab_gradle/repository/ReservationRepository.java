package com.swedist.computer_lab_gradle.repository;

import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.entity.ComputerStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ComputerStudent, Long> {
    Optional<List<ComputerStudent>> findAllByStudent(AppUser student);
}
