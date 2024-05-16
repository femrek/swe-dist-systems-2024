package com.swedist.computer_lab_gradle.dto.request;

import com.swedist.computer_lab_gradle.entity.Computer;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputerCreateRequest {
    private String brand;
    private String model;

    public Computer toEntity() {
        return Computer.builder()
                .brand(brand)
                .model(model)
                .build();
    }
}
