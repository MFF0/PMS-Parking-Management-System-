package com.Meshal.PMS.service;

import com.Meshal.PMS.Repositories.VehicleRepository;
import com.Meshal.PMS.Request.VehicleRequest;
import com.Meshal.PMS.data.UserData;
import com.Meshal.PMS.domain.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserData userData;

    public String addVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle =
                Vehicle.builder()
                .manufacturer(vehicleRequest.getManufacturer())
                .model(vehicleRequest.getModel())
                .vehicleType(vehicleRequest.getVehicleType())
                .licensePlate(vehicleRequest.getLicensePlate())
                .build();

        vehicle.setUser(userData.getUser());
        vehicleRepository.save(vehicle);

        return "Successfully added the vehicle for " + userData.getUser().getFirstName();
    }

    public List<Vehicle> getAllUserVehicles() {
        int userId = userData.getUser().getUserId();
        return vehicleRepository.findAll().stream()
                .filter(vehicle -> vehicle.getUser().getUserId() == userId)
                .collect(Collectors.toList());
    }


    public Optional<Vehicle> findOptionalVehicleByLicencePlate(String lp) {
        return vehicleRepository.findVehicleByLicensePlate(lp);
    }

    public void deleteOneVehicle(int vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        vehicle.ifPresent(vehicleRepository::delete);
    }

    public void deleteAllUserVehicles(){
        vehicleRepository.deleteAll(getAllUserVehicles());
    }

//
//    public String printAllVehicles() {
//        StringBuilder sb = new StringBuilder();
//        vehicleRepository.findAll().forEach(vehicle -> sb.append(vehicle.getCreatedDate()));
//        return sb.toString();
//    }
}
