package com.Meshal.PMS.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationRequest {
    private int reservationId;
    private int vehicleId;
    private LocalDateTime startDate;
    private int numberOfHours;
    private int slotId;
    private final LocalDate dateOfreservation = LocalDate.now();
    private String license_plate;
    private String reservedSlotType;
    private LocalDate reservedSlotStartDate;
    private LocalDate reservedSlotEndDate;
}
