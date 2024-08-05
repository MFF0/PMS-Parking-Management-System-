package com.Meshal.PMS.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FailureResponse {
    private boolean result;
    private String message;
    private LocalDateTime time = LocalDateTime.now();
}
