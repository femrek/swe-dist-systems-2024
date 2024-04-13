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
        Student student = studentRepository.findById(reservationPostRequest.getStudentId()).orElseThrow();
        Computer computer = computerRepository.findById(reservationPostRequest.getComputerId()).orElseThrow();
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
        Student student = studentRepository.findById(reservationPostRequest.getStudentId()).orElseThrow();
        Computer computer = computerRepository.findById(reservationPostRequest.getComputerId()).orElseThrow();
        ComputerStudent computerStudent = computerStudentRepository.findById(id).orElseThrow();
        computerStudent.setStudent(student);
        computerStudent.setComputer(computer);
        computerStudent.setReservationDate(Date.valueOf(reservationPostRequest.getReservationDate()));
        computerStudent.setDuration(Duration.ofDays(reservationPostRequest.getDuration()));
        ComputerStudent updatedComputerStudent = computerStudentRepository.save(computerStudent);
        return new ComputerStudentDTO(updatedComputerStudent);
    }

    public void deleteReservation(Long id)
    {
        ComputerStudent computerStudent = computerStudentRepository.findById(id).orElseThrow();
        computerStudent.getStudent().delete(computerStudent);
        computerStudent.getComputer().delete(computerStudent);
        computerStudentRepository.delete(computerStudent);
    }
}
