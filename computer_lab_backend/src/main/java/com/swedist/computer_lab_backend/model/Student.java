package com.swedist.computer_lab_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String department;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ComputerStudent.class, mappedBy = "student", cascade = CascadeType.ALL)
    private Set<ComputerStudent> reservations;

    public void delete(ComputerStudent computerStudent) {
        if (reservations != null) {
            reservations.remove(computerStudent);
        }
    }
}
