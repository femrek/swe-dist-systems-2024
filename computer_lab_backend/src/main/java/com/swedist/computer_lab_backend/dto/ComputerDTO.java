package com.swedist.computer_lab_backend.dto;

import com.swedist.computer_lab_backend.model.Computer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComputerDTO {
    private Long id;
    private String brand;
    private String model;

    public ComputerDTO(Computer computer) {
        id = computer.getId();
        brand = computer.getBrand();
        model = computer.getModel();
    }
}
