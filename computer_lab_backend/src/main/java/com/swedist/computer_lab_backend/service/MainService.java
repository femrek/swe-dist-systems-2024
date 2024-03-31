package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.repository.ComputerRepository;
import com.swedist.computer_lab_backend.repository.ComputerStudentRepository;
import com.swedist.computer_lab_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final ComputerRepository computerRepository;
    private final ComputerStudentRepository computerStudentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public MainService(ComputerRepository computerRepository, ComputerStudentRepository computerStudentRepository, StudentRepository studentRepository) {
        this.computerRepository = computerRepository;
        this.computerStudentRepository = computerStudentRepository;
        this.studentRepository = studentRepository;
    }

    public ComputerDTO createComputer(ComputerDTO computerDto) {
        Computer computer = new Computer();
        computer.setModel(computerDto.getModel());
        computer.setBrand(computerDto.getBrand());
        Computer savedComputer = computerRepository.save(computer);
        return new ComputerDTO(savedComputer);
    }
}
