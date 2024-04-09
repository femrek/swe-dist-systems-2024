package com.swedist.computer_lab_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    private String model;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ComputerStudent.class, mappedBy = "computer")
    private Set<ComputerStudent> reservations;
}
