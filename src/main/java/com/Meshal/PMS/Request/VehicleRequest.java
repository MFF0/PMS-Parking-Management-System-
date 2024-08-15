package com.Meshal.PMS.Request;

import com.Meshal.PMS.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private int vehicleId;
    private String manufacturer;
    private String model;
    private VehicleType vehicleType;
    private String licensePlate;
}
