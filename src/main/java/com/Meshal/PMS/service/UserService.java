package com.Meshal.PMS.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.Request.RegisterRequest;
import com.Meshal.PMS.data.UserData;
import com.Meshal.PMS.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserData userData;

    public String Register(RegisterRequest registerRequest) {

        String encryptedPassword = BCrypt.withDefaults().hashToString(12, registerRequest.getPassword().toCharArray());

            userRepository.save(new User(
                    registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getEmail(),
                    encryptedPassword,
                    registerRequest.getDob()));
            return "Success";
    }
    public Optional<User> optionalFindUser() {
        return userRepository.findById(userData.getUser().getUserId());
    }
}
