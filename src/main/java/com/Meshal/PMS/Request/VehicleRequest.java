package com.Meshal.PMS.Request;

import com.Meshal.PMS.enums.VehicleType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    private String manufacturer;
    private String model;
    private VehicleType vehicleType;
    @NonNull
    private String licensePlate;

}
