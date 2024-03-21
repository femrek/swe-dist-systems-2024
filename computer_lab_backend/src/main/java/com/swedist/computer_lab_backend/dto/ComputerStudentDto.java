package com.swedist.computer_lab_backend.dto;

import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.model.ComputerStudent;
import com.swedist.computer_lab_backend.model.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComputerStudentDto {
    private Long id;
    private StudentDto student;
    private ComputerDto computer;
    private Date reservationDate;
    private Duration duration;

    public ComputerStudentDto(ComputerStudent computerStudent) {
        id = computerStudent.getId();
        student = new StudentDto(computerStudent.getStudent());
        computer = new ComputerDto(computerStudent.getComputer());
        reservationDate = computerStudent.getReservationDate();
        duration = computerStudent.getDuration();
    }
}
