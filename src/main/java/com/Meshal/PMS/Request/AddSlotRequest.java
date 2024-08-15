package com.Meshal.PMS.Request;

import com.Meshal.PMS.dto.SlotDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSlotRequest {
    private int garageId;
    private int numOfSlots;
    private SlotDto slotDto;
}
