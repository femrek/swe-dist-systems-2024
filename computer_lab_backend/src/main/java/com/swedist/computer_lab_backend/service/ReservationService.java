package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.ComputerStudentDTO;
import com.swedist.computer_lab_backend.model.ComputerStudent;
import com.swedist.computer_lab_backend.repository.ComputerStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ComputerStudentRepository computerStudentRepository;

    @Autowired
    public ReservationService(ComputerStudentRepository computerStudentRepository) {
        this.computerStudentRepository = computerStudentRepository;
    }

    public List<ComputerStudentDTO> getReservations() {
        List<ComputerStudent> reservations = computerStudentRepository.findAll();
        return reservations.stream().map(ComputerStudentDTO::new).toList();
    }
}
