package com.swedist.computer_lab_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String department;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ComputerStudent.class, mappedBy = "student")
    private Set<ComputerStudent> reservations;
}
