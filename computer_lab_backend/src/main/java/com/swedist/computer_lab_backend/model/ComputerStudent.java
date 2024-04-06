package com.swedist.computer_lab_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"student", "computer"})
@Entity
@Table(name = "computer_student")
public class ComputerStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Student.class)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Computer.class)
    private Computer computer;

    private Date reservationDate;

    private Duration duration;
}
