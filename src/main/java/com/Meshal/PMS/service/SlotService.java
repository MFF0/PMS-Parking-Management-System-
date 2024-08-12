package com.Meshal.PMS.service;

import com.Meshal.PMS.Repositories.SlotRepository;
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

}
