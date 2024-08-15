package com.Meshal.PMS.service;

import com.Meshal.PMS.Repositories.SlotRepository;
import com.Meshal.PMS.Request.AddSlotRequest;
import com.Meshal.PMS.domain.Slot;
import com.Meshal.PMS.dto.SlotDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotService {


    private final SlotRepository slotRepository;
    private final GarageService garageService;

    public boolean postSlot(SlotDto slotDto) {
        try{
        slotRepository.save(new Slot(
                slotDto.isOccupied(),
                slotDto.getVehicleType(),
                slotDto.getGarage()));
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Slot> getAvailableSlotsForGarage(int insertedGarageId) {
        return slotRepository.findAll().stream()
                .filter(slot -> !slot.isOccupied() &&
                        slot.getGarage().getGarageId() == insertedGarageId)
                .collect(Collectors.toList());
    }

    public SlotDto getSlotById(int slotId) {
        Optional<Slot> slot = slotRepository.findById(slotId);
        if(slot.isPresent()){
            return slot.get().getSlotDto();
        } else {
            throw new EntityNotFoundException("Slot not found");
        }
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public int calcNumOfSlotsToAdd(int garageId, int numOfSlots) {
        int numOfExistingSlots = slotRepository.countAllByGarageGarageId(garageId);
        int capacity = garageService.getGarageById(garageId).getCapacity();
        int availableSlots = capacity - numOfExistingSlots;

        return Math.min(numOfSlots, availableSlots);
    }


    public void addSlot(AddSlotRequest addSlotRequest) {
        SlotDto slotDto = addSlotRequest.getSlotDto();
        int garageId = addSlotRequest.getGarageId();
        int numOfSlots = addSlotRequest.getNumOfSlots();
        int numOfSlotsToAdd = calcNumOfSlotsToAdd(garageId, numOfSlots);
        slotDto.setGarage(garageService.getGarageById(garageId));
        for (int i = 0; i < numOfSlotsToAdd; i++) {
            boolean success = postSlot(slotDto);
            if (!success) {
                throw new IllegalArgumentException("Failed to add all slots ");
            }
        }
    }
}
