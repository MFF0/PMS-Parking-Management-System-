package com.Meshal.PMS.service;

import com.Meshal.PMS.Repositories.GarageRepository;
import com.Meshal.PMS.domain.Garage;
import com.Meshal.PMS.dto.GarageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarageService {
    private final GarageRepository garageRepository;

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    public Garage getGarageById(int id) {
        Optional<Garage> garage = garageRepository.findById(id);
        if (garage.isPresent()) {
            return garage.get();
        } else {
            throw new IllegalArgumentException("garage doesn't exist");
        }
    }

    public boolean addGarage(GarageDto garageDto) {
        try{
            garageRepository.save(new Garage(
                    garageDto.getZipCode(),
                    garageDto.getLargeVehicleRate(),
                    garageDto.getSmallVehicleRate(),
                    garageDto.getMediumVehicleRate(),
                    garageDto.getSlots()));
            return true;
        } catch(Exception e)    {
            return false;
        }
    }
}
