package com.Meshal.PMS.dto;

import com.Meshal.PMS.domain.Garage;
import com.Meshal.PMS.domain.Reservation;
import com.Meshal.PMS.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Set;

@Data
public class SlotDto {
    private boolean isOccupied;
    private VehicleType vehicleType;
    @JsonIgnore
    private Garage garage;
    private Set<Reservation> reservations;

}
