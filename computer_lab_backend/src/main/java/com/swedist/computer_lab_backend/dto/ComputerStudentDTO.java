package com.swedist.computer_lab_backend.dto;

import com.swedist.computer_lab_backend.model.ComputerStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComputerStudentDTO {
    private Long id;
    private StudentDTO student;
    private ComputerDTO computer;
    private Date reservationDate;
    private Long duration;

    public ComputerStudentDTO(ComputerStudent computerStudent) {
        id = computerStudent.getId();
        student = new StudentDTO(computerStudent.getStudent());
        computer = new ComputerDTO(computerStudent.getComputer());
        reservationDate = computerStudent.getReservationDate();
        duration = computerStudent.getDuration().toDays();
    }
    public String getDateFormatted(){

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(reservationDate);

    }
}
