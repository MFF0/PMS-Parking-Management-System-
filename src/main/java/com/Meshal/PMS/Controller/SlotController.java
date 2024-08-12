package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Request.ReservationRequest;
import com.Meshal.PMS.domain.Slot;
import com.Meshal.PMS.dto.SlotDto;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.GarageService;
import com.Meshal.PMS.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slot")
@RequiredArgsConstructor
public class SlotController {
    private final SlotService slotService;
    private final GarageService garageService;

    @AuthorizedPMS
    @PostMapping("/reserveSlot")
    public ReservationRequest reservationRequest(@RequestBody ReservationRequest reservationRequest) {
        return reservationRequest;
    }

    @AuthorizedPMS
    @PostMapping("/addSlot/{garageId}/{numOfSlots}")
    public ResponseEntity<?> addSlot(@PathVariable int garageId, @PathVariable int numOfSlots, @RequestBody SlotDto slotDto) {
        slotDto.setGarage(garageService.getGarageById(garageId));
        for (int i = 0; i < numOfSlots; i++) {
            boolean success = slotService.postSlot(slotDto);
            if (!success) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Failed to add slot at index: " + i);
            }
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(numOfSlots + " slots successfully added to the garage with ID: " + garageId);
    }

    @AuthorizedPMS
    @GetMapping("/getSlotById/{slotId}")
    public SlotDto getSlotById(@PathVariable int slotId) {
        return slotService.getSlotById(slotId);
    }

    @AuthorizedPMS
    @GetMapping("/getAllSlots")
    public List<Slot> getAllSlots() {
        return slotService.getAllSlots();
    }

    @AuthorizedPMS
    @GetMapping("/getAllAvailableSlotsForGarage/{garageId}")
    public List<Slot> getAllAvailableSlotsForGarage(@PathVariable int garageId) {
        return slotService.getAvailableSlotsForGarage(garageId);
    }
}
