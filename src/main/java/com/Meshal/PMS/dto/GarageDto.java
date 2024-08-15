package com.Meshal.PMS.dto;

import com.Meshal.PMS.domain.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarageDto {
    private int zipCode;
    private double largeVehicleRate;
    private double smallVehicleRate;
    private double mediumVehicleRate;
    private int capacity;
    private List<Slot> slots;
}
