package com.Meshal.PMS.Response;

public record GeneralResponse(
        boolean success,
        String customMessage
) {
}
