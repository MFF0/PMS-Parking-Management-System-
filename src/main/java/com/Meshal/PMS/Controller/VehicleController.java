package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Repositories.VehicleRepository;
import com.Meshal.PMS.Request.VehicleRequest;
import com.Meshal.PMS.domain.Vehicle;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    @PostMapping("/add")
    @AuthorizedPMS
    public String vehicle(@RequestBody VehicleRequest vehicleRequest){

        return vehicleService.addVehicle(vehicleRequest);
    }
    @GetMapping("/get")
    @AuthorizedPMS
    public List<Vehicle> getAllUserVehicles(){
        return vehicleService.getAllUserVehicles();
    }

    @DeleteMapping("/delete/{vehicleId}")
    @AuthorizedPMS
    public String deleteVehicle(@PathVariable int vehicleId){
        vehicleService.deleteOneVehicle(vehicleId);
        return "Successfully deleted vehicle";
    }

    @PutMapping("/update/{vehicleId}")
    @AuthorizedPMS
    public String updateVehicle(@PathVariable int vehicleId
            , @RequestBody VehicleRequest updatedVehicle){
    Optional<Vehicle> existingVehicle = vehicleRepository.findByVehicleId(vehicleId);
    if(existingVehicle.isPresent()){
        Vehicle vehicle = existingVehicle.get();
        vehicle.setManufacturer(updatedVehicle.getManufacturer());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setVehicleType(updatedVehicle.getVehicleType());
        vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        vehicle.setModifiedDate(new Date());
        vehicleRepository.save(vehicle);
        return "Successfully updated the vehicle with ID: " + vehicle.getVehicleId();
        }
    else {
        return "Vehicle not found";
        }
    }
}
