package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Request.LoginRequest;
import com.Meshal.PMS.Response.LoginResponse;
import com.Meshal.PMS.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}
