package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.repository.ComputerStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ComputerStudentRepository computerStudentRepository;

    @Autowired
    public ReservationService(ComputerStudentRepository computerStudentRepository) {
        this.computerStudentRepository = computerStudentRepository;
    }
}
