package com.swedist.computer_lab_gradle.controller.reservation;

import com.swedist.computer_lab_gradle.dto.ComputerStudentDTO;
import com.swedist.computer_lab_gradle.dto.request.ReservationPostRequest;
import com.swedist.computer_lab_gradle.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationRest {
    private final ReservationService reservationService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<ComputerStudentDTO>> reservationPage() {
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ComputerStudentDTO> createReservation(
            @RequestBody ReservationPostRequest reservationPostRequest) {
        return ResponseEntity.ok(reservationService.createReservation(reservationPostRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerStudentDTO> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationPostRequest reservationPostRequest) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservationPostRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComputerStudentDTO> deleteReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<ComputerStudentDTO> returnReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.returnReservation(id));
    }
}
