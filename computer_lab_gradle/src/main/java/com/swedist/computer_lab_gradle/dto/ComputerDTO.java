package com.swedist.computer_lab_gradle.dto;

import com.swedist.computer_lab_gradle.entity.Computer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComputerDTO {
    private Long id;
    private String brand;
    private String model;

    public ComputerDTO(Computer computer) {
        id = computer.getId();
        brand = computer.getBrand();
        model = computer.getModel();
    }

    public String validate() {
        if (brand == null || brand.isBlank()) {
            return "brand must be given";
        }
        if (model == null || model.isBlank()) {
            return "model must be given";
        }

        return null;
    }

    public String toVisualString() {
        return new StringBuilder()
                .append(brand)
                .append(", ")
                .append(model)
                .toString();
    }
}

