package com.Meshal.PMS.dto;

import com.Meshal.PMS.domain.User;
import com.Meshal.PMS.domain.Vehicle;
import com.Meshal.PMS.enums.VehicleType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class VehicleDto {
    private int vehicleId;
    private String manufacturer;
    private String model;
    @Enumerated
    private VehicleType vehicleType;
    private String licensePlate;
    private Date createdDate;
    private Date modifiedDate;
    private UserDto user;

}
