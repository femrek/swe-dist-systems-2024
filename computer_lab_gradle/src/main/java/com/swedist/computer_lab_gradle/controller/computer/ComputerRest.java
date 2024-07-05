package com.swedist.computer_lab_gradle.controller.computer;

import com.swedist.computer_lab_gradle.dto.ComputerDTO;
import com.swedist.computer_lab_gradle.dto.request.ComputerCreateRequest;
import com.swedist.computer_lab_gradle.dto.request.ComputerUpdateRequest;
import com.swedist.computer_lab_gradle.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computer")
@RequiredArgsConstructor
public class ComputerRest {
    private final ComputerService computerService;

    @GetMapping("")
    public ResponseEntity<List<ComputerDTO>> getComputers() {
        return ResponseEntity.ok(computerService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<ComputerDTO> createComputer(@RequestBody ComputerCreateRequest computerCreateRequest) {
        return ResponseEntity.ok(computerService.save(computerCreateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerDTO> updateComputer(@PathVariable Long id,
                                                      @RequestBody ComputerUpdateRequest computerUpdateRequest) {
        computerUpdateRequest.setId(id);
        return ResponseEntity.ok(computerService.update(computerUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComputer(@PathVariable Long id) {
        computerService.deleteById(id);
        return ResponseEntity.ok("{}");
    }
}
