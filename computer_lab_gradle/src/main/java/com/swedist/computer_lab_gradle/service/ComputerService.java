package com.swedist.computer_lab_gradle.service;

import com.swedist.computer_lab_gradle.dto.ComputerDTO;
import com.swedist.computer_lab_gradle.dto.request.ComputerCreateRequest;
import com.swedist.computer_lab_gradle.dto.request.ComputerUpdateRequest;
import com.swedist.computer_lab_gradle.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository computerRepository;

    public List<ComputerDTO> findAll() {
        return computerRepository.findAll().stream().map(ComputerDTO::new).toList();
    }

    public ComputerDTO save(ComputerCreateRequest computerCreateRequest) {
        return new ComputerDTO(computerRepository.save(computerCreateRequest.toEntity()));
    }

    public ComputerDTO update(ComputerUpdateRequest computerUpdateRequest) {
        return new ComputerDTO(computerRepository.save(computerUpdateRequest.toEntity()));
    }

    public void deleteById(Long id) {
        computerRepository.deleteById(id);
    }
}
