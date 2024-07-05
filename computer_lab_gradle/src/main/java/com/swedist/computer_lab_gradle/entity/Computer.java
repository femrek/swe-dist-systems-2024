package com.swedist.computer_lab_gradle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ComputerStudent.class, mappedBy = "computer", cascade = CascadeType.ALL)
    private Set<ComputerStudent> reservations;

    public void delete(ComputerStudent computerStudent) {
        if (reservations != null) {
            reservations.remove(computerStudent);
        }
    }
}
