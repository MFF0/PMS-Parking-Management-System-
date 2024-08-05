package com.Meshal.PMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;

    private String email;
}
