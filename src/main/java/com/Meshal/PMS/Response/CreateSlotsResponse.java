package com.Meshal.PMS.Response;

public record CreateSlotsResponse(
        boolean result,
        String resultDetail,
        Integer totalCreatedSlots

) {
}
