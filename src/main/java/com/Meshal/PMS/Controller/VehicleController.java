package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Request.VehicleRequest;
import com.Meshal.PMS.domain.Vehicle;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;


    @PostMapping("/add")
    @AuthorizedPMS
    public ResponseEntity<?> vehicle(@RequestBody VehicleRequest vehicleRequest) {
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

    @PutMapping("/update")
    @AuthorizedPMS
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleRequest updatedVehicle) {
        return vehicleService.updateVehicle(updatedVehicle);
    }
}
