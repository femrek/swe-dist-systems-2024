package com.swedist.computer_lab_gradle.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationPostRequest {
    private Long id;
    private Long studentId;
    private Long computerId;
    private String reservationDate;
    private Integer duration;

    public String validate() {
        if (studentId == null) {
            return "No given student";
        }
        if (computerId == null) {
            return "No given computer";
        }
        if (reservationDate == null || reservationDate.isBlank()) {
            return "Reservation date must be given";
        }
        if (duration == null) {
            return "Duration must be given";
        }
        if (duration <= 0) {
            return "Duration cannot be negative or zero";
        }

        return null;
    }
}
