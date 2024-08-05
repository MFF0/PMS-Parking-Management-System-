package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Request.ReservationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")

public class ReservationController {
    @PostMapping
    public ReservationRequest reservationRequest(@RequestBody ReservationRequest reservationRequest) {
        return reservationRequest;
    }

}
