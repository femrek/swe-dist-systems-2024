package com.swedist.computer_lab_gradle.controller.reservation;

import com.swedist.computer_lab_gradle.dto.ComputerStudentDTO;
import com.swedist.computer_lab_gradle.dto.request.ReservationPostRequest;
import com.swedist.computer_lab_gradle.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users-reservation")
@RequiredArgsConstructor
public class UsersReservationRest {
    private final ReservationService reservationService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<ComputerStudentDTO>> usersReservations(
            @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(reservationService.getReservationsOfTheUser(authHeader));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ComputerStudentDTO> createReservation(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ReservationPostRequest reservationPostRequest) {
        return ResponseEntity.ok(reservationService.createReservation(authHeader, reservationPostRequest));
    }
}
