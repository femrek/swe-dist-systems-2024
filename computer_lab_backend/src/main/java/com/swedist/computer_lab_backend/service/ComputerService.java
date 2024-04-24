package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.model.ComputerStudent;
import com.swedist.computer_lab_backend.model.Student;
import com.swedist.computer_lab_backend.repository.ComputerRepository;
import com.swedist.computer_lab_backend.repository.ComputerStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ComputerService {
    private final ComputerRepository computerRepository;
    private final ComputerStudentRepository computerStudentRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository,
                           ComputerStudentRepository computerStudentRepository) {
        this.computerRepository = computerRepository;
        this.computerStudentRepository = computerStudentRepository;
    }

    public List<ComputerDTO> getComputers() {
        List<Computer> computers = computerRepository.findAll();
        return computers.stream().map(ComputerDTO::new).toList();
    }

    public ComputerDTO createComputer(ComputerDTO computerDto) {
        String validationError = computerDto.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        Computer computer = new Computer();
        computer.setModel(computerDto.getModel());
        computer.setBrand(computerDto.getBrand());
        Computer savedComputer = computerRepository.save(computer);
        return new ComputerDTO(savedComputer);
    }

    public ComputerDTO updateComputer(ComputerDTO computerDto) {
        String validationError = computerDto.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        Computer computer = computerRepository.findById(computerDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("Computer couldn't found with given id")
        );
        computer.setModel(computerDto.getModel());
        computer.setBrand(computerDto.getBrand());
        Computer savedComputer = computerRepository.save(computer);
        return new ComputerDTO(savedComputer);
    }

    public ComputerDTO deleteComputer(Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Computer couldn't found with given id")
        );
        Set<ComputerStudent> tiedReservations
                = computerStudentRepository.findAllByComputer(computer);
        if (!tiedReservations.isEmpty()) {
            throw new RuntimeException("The computer cannot be deleted because the computer has already reservations");
        }
        computerRepository.deleteById(id);
        return new ComputerDTO(computer);
    }
}
