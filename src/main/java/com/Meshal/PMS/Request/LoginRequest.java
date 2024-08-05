package com.Meshal.PMS.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {

    private String Email;
    private String Password;

}
