package com.swedist.computer_lab_gradle.dto;

import com.swedist.computer_lab_gradle.entity.ComputerStudent;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComputerStudentDTO {
    private Long id;
    private UserDTO student;
    private ComputerDTO computer;
    private Date reservationDate;
    private Long duration;
    private Boolean isReturned;

    public ComputerStudentDTO(ComputerStudent computerStudent) {
        id = computerStudent.getId();
        student = new UserDTO(computerStudent.getStudent());
        computer = new ComputerDTO(computerStudent.getComputer());
        reservationDate = computerStudent.getReservationDate();
        duration = computerStudent.getDuration().toDays();
        isReturned = computerStudent.getIsReturned();
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(reservationDate);
    }

    public String toVisualString() {
        return new StringBuilder()
                .append(student.toVisualString())
                .append(", ")
                .append(computer.toVisualString())
                .append(", ")
                .append(getDateFormatted())
                .append(", ")
                .append(duration)
                .append(" Days")
                .toString();
    }
}
