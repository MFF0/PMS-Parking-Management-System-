package com.Meshal.PMS.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.Request.LoginRequest;
import com.Meshal.PMS.Response.LoginResponse;
import com.Meshal.PMS.domain.User;
import com.Meshal.PMS.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.Meshal.PMS.service.JwtService.genToken;

@Slf4j
@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {

        Optional<User> userBox = userRepository.
                findUserByEmail(loginRequest.getEmail());

        if(userBox.isPresent() &&
                BCrypt.verifyer().verify(loginRequest.getPassword().toCharArray(),
                        userBox.get().getPassword()).verified) {
            String token = genToken(userBox.get());
            return new LoginResponse(token);
        }
        throw new UserNotFoundException("Either username or password is incorrect");
    }
}
