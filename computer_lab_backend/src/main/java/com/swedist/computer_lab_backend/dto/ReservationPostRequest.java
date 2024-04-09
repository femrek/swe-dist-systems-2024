package com.swedist.computer_lab_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationPostRequest {
    private Long id;
    private Long studentId;
    private Long computerId;
    private String reservationDate;
    private Integer duration;
}
