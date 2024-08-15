package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Request.AddSlotRequest;
import com.Meshal.PMS.Request.ReservationRequest;
import com.Meshal.PMS.Response.CreateSlotsResponse;
import com.Meshal.PMS.domain.Slot;
import com.Meshal.PMS.dto.SlotDto;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.SlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/slot")
@RequiredArgsConstructor
public class SlotController {
    private final SlotService slotService;

    //TODO
    @AuthorizedPMS
    @PostMapping("/reserveSlot")
    public ReservationRequest reservationRequest(@RequestBody ReservationRequest reservationRequest) {
        return reservationRequest;
    }

    @AuthorizedPMS
    @PostMapping("/addSlot")
    public ResponseEntity<?> addSlot(@RequestBody AddSlotRequest addSlotRequest) {
        slotService.addSlot(addSlotRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CreateSlotsResponse(true, "Success", slotService.
                        calcNumOfSlotsToAdd(addSlotRequest.getGarageId(),
                                addSlotRequest.getNumOfSlots())));
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
//
//    @AuthorizedPMS
//    @GetMapping("/getAllAvailableSlotsForGarage/{garageId}")
//    public List<Slot> getAllAvailableSlotsForGarage(@PathVariable int garageId) {
//        return slotService.getAvailableSlotsForGarage(garageId);
//    }
}
