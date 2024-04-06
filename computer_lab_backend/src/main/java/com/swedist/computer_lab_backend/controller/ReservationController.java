package com.swedist.computer_lab_backend.controller;

import com.swedist.computer_lab_backend.service.ReservationService;
import com.swedist.computer_lab_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.swedist.computer_lab_backend.constants.AppConstants.*;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping({"/", ""})
    public String showReservations(Model model) {
        model.addAttribute(RESERVATION_LIST_KEY, reservationService.getReservations());
        model.addAttribute(SUCCESS_MESSAGE_KEY, "Reservation list fetched successfully");
        return "reservation/index";
    }
}
