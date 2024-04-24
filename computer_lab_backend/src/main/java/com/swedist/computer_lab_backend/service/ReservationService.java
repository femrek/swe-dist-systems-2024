package com.swedist.computer_lab_backend.service;

import com.swedist.computer_lab_backend.dto.ComputerStudentDTO;
import com.swedist.computer_lab_backend.dto.ReservationPostRequest;
import com.swedist.computer_lab_backend.model.Computer;
import com.swedist.computer_lab_backend.model.ComputerStudent;
import com.swedist.computer_lab_backend.model.Student;
import com.swedist.computer_lab_backend.repository.ComputerRepository;
import com.swedist.computer_lab_backend.repository.ComputerStudentRepository;
import com.swedist.computer_lab_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.util.List;

@Service
public class ReservationService {
    private final ComputerStudentRepository computerStudentRepository;
    private final ComputerRepository computerRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ReservationService(ComputerStudentRepository computerStudentRepository,
                              ComputerRepository computerRepository,
                              StudentRepository studentRepository) {
        this.computerStudentRepository = computerStudentRepository;
        this.computerRepository = computerRepository;
        this.studentRepository = studentRepository;
    }

    public List<ComputerStudentDTO> getReservations() {
        List<ComputerStudent> reservations = computerStudentRepository.findAll();
        return reservations.stream().map(ComputerStudentDTO::new).toList();
    }

    public ComputerStudentDTO createReservation(ReservationPostRequest reservationPostRequest) {
        String validationError = reservationPostRequest.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        Student student = studentRepository.findById(reservationPostRequest.getStudentId()).orElseThrow(
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
        System.out.println(savedComputerStudent);
        return new ComputerStudentDTO(savedComputerStudent);
    }

    public ComputerStudentDTO updateReservation(Long id, ReservationPostRequest reservationPostRequest) {
        String validationError = reservationPostRequest.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        Student student = studentRepository.findById(reservationPostRequest.getStudentId()).orElseThrow(
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
}
