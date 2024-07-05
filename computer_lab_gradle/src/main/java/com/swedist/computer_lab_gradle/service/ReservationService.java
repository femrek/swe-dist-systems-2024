package com.swedist.computer_lab_gradle.service;

import com.swedist.computer_lab_gradle.dto.ComputerStudentDTO;
import com.swedist.computer_lab_gradle.dto.request.ReservationPostRequest;
import com.swedist.computer_lab_gradle.entity.AppUser;
import com.swedist.computer_lab_gradle.entity.Computer;
import com.swedist.computer_lab_gradle.entity.ComputerStudent;
import com.swedist.computer_lab_gradle.repository.ComputerRepository;
import com.swedist.computer_lab_gradle.repository.ReservationRepository;
import com.swedist.computer_lab_gradle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final JwtService jwtService;
    private final ComputerRepository computerRepository;
    private final UserRepository studentRepository;
    private final ReservationRepository computerStudentRepository;

    public List<ComputerStudentDTO> getReservations() {
        List<ComputerStudent> reservations = computerStudentRepository.findAll();
        return reservations.stream().map(ComputerStudentDTO::new).toList();
    }

    public ComputerStudentDTO createReservation(ReservationPostRequest reservationPostRequest) {
        String validationError = reservationPostRequest.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        AppUser student = studentRepository.findById(reservationPostRequest.getStudentId()).orElseThrow(
                () -> new IllegalArgumentException("Student couldn't found with given id")
        );
        Computer computer = computerRepository.findById(reservationPostRequest.getComputerId()).orElseThrow(
                () -> new IllegalArgumentException("Computer couldn't found with given id")
        );
        ComputerStudent computerStudent = new ComputerStudent();
        computerStudent.setStudent(student);
        computerStudent.setComputer(computer);
        computerStudent.setReservationDate(Date.valueOf(reservationPostRequest.getReservationDate()));
        computerStudent.setDuration(Duration.ofDays(reservationPostRequest.getDuration()));
        ComputerStudent savedComputerStudent = computerStudentRepository.save(computerStudent);
        return new ComputerStudentDTO(savedComputerStudent);
    }

    public ComputerStudentDTO updateReservation(Long id, ReservationPostRequest reservationPostRequest) {
        String validationError = reservationPostRequest.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        AppUser student = studentRepository.findById(reservationPostRequest.getStudentId()).orElseThrow(
                () -> new IllegalArgumentException("Student couldn't found with given id")
        );
        Computer computer = computerRepository.findById(reservationPostRequest.getComputerId()).orElseThrow(
                () -> new IllegalArgumentException("Computer couldn't found with given id")
        );
        ComputerStudent computerStudent = computerStudentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Reservation couldn't found with given id")
        );
        computerStudent.setStudent(student);
        computerStudent.setComputer(computer);
        computerStudent.setReservationDate(Date.valueOf(reservationPostRequest.getReservationDate()));
        computerStudent.setDuration(Duration.ofDays(reservationPostRequest.getDuration()));
        ComputerStudent updatedComputerStudent = computerStudentRepository.save(computerStudent);
        return new ComputerStudentDTO(updatedComputerStudent);
    }

    public ComputerStudentDTO deleteReservation(Long id) {
        ComputerStudent computerStudent = computerStudentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Reservation with id could not found")
        );
        computerStudent.getStudent().delete(computerStudent);
        computerStudent.getComputer().delete(computerStudent);
        computerStudentRepository.delete(computerStudent);
        return new ComputerStudentDTO(computerStudent);
    }

    public ComputerStudentDTO returnReservation(Long id) {
        ComputerStudent computerStudent = computerStudentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Reservation with id could not found")
        );
        computerStudent.setIsReturned(true);
        computerStudentRepository.save(computerStudent);
        return new ComputerStudentDTO(computerStudent);
    }

    public List<ComputerStudentDTO> getReservationsOfTheUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization header must be given");
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        AppUser student = studentRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        List<ComputerStudent> reservations = computerStudentRepository.findAllByStudent(student).orElseThrow(
                () -> new IllegalArgumentException("Reservations not found")
        );

        return reservations.stream().map(ComputerStudentDTO::new).toList();
    }

    public ComputerStudentDTO createReservation(String authHeader,
                                                ReservationPostRequest reservationPostRequest) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization header must be given");
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        AppUser student = studentRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        reservationPostRequest.setStudentId(student.getId());

        return createReservation(reservationPostRequest);
    }
}