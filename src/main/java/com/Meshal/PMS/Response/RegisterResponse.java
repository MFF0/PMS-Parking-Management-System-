package com.Meshal.PMS.Response;

import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.Request.RegisterRequest;
import com.Meshal.PMS.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterResponse {

    private final UserRepository userRepository;

    public String getMessage(RegisterRequest registerRequest) {
        Optional<User> user = userRepository.findUserByEmailAndPassword(registerRequest.getEmail(), registerRequest.getPassword());
        if (user.isPresent()) {
            return "User registration successful!";
        } else {
            return "Email or password is incorrect.";
        }
    }
}
