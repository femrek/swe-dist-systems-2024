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
public class ComputerDto {
    private Long id;
    private String brand;
    private String model;

    public ComputerDto(Computer computer) {
        id = computer.getId();
        brand = computer.getBrand();
        model = computer.getModel();
    }
}
