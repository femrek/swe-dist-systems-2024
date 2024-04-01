package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.ComputerDTO;
import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<ComputerDTO> getComputers() {
        List<Computer> computers = computerRepository.findAll();
        return computers.stream().map(ComputerDTO::new).toList();
    }

    public ComputerDTO getComputer(Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow();
        return new ComputerDTO(computer);
    }

    public ComputerDTO createComputer(ComputerDTO computerDto) {
        Computer computer = new Computer();
        computer.setModel(computerDto.getModel());
        computer.setBrand(computerDto.getBrand());
        Computer savedComputer = computerRepository.save(computer);
        return new ComputerDTO(savedComputer);
    }

    public ComputerDTO updateComputer(ComputerDTO computerDto) {
        Computer computer = computerRepository.findById(computerDto.getId()).orElseThrow();
        computer.setModel(computerDto.getModel());
        computer.setBrand(computerDto.getBrand());
        Computer savedComputer = computerRepository.save(computer);
        return new ComputerDTO(savedComputer);
    }

    public void deleteComputer(Long id) {
        computerRepository.deleteById(id);
    }
}
