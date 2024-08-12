package com.Meshal.PMS.Request;

import com.Meshal.PMS.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationRequest {
    private String reservationDate;
    private long reservationDuration;
    private Vehicle vehicle;

}
