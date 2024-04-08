package com.swedist.computer_lab_backend.dto;

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
public class ReservationPostRequest {
    private Long id;
    private Long studentId;
    private Long computerId;
    private Date reservationDate;
    private Duration duration;
}
