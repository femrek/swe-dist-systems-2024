package com.swedist.computer_lab_gradle.controller.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    @RequestMapping({"", "/"})
    public String reservationPage() {
        return "reservation/index";
    }
}
