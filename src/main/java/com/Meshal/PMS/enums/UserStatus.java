package com.Meshal.PMS.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    SUSPENDED("SUSPENDED");

    private final String status;

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static UserStatus forValue(String value) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}