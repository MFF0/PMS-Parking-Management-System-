package com.Meshal.PMS.Controller;

import com.Meshal.PMS.domain.Garage;
import com.Meshal.PMS.dto.GarageDto;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garage")
@RequiredArgsConstructor
public class GarageController {

    private final GarageService garageService;

    @AuthorizedPMS
    @PostMapping("/add")
    public ResponseEntity<?> addGarage(@RequestBody GarageDto garageDto) {
        boolean success = garageService.addGarage(garageDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @AuthorizedPMS
    @GetMapping("/getAllGarages")
    public List<Garage> getAllGarages(){
        return garageService.getAllGarages();
    }

}
