package com.Meshal.PMS.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.Request.RegisterRequest;
import com.Meshal.PMS.Request.UserStatusRequest;
import com.Meshal.PMS.Response.UpdateResponse;
import com.Meshal.PMS.data.UserData;
import com.Meshal.PMS.domain.User;
import com.Meshal.PMS.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
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

    public Optional<User> optionalFindActiveUser() {
        return userRepository.findById(userData.getUser().getUserId());
    }
    public void setUserStatus(User user, UserStatus userStatus) {
        user.setUserStatus(userStatus);
        userRepository.save(user);
    }

    public void updateUser(RegisterRequest updatedUser) {
        Optional<User> existingUser = optionalFindActiveUser();
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(BCrypt.withDefaults().hashToString(12, updatedUser.getPassword().toCharArray()));
            user.setDateOfBirth(updatedUser.getDob());
            user.setModifiedDate(new Date());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public ResponseEntity<?> updateUserStatus(UserStatusRequest userStatusRequest) {
        Optional<User> existingUser = userRepository.findById(userStatusRequest.getUserId());

        if (existingUser.isPresent()) {
            setUserStatus(existingUser.get(), userStatusRequest.getUserStatus());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateResponse(true, "Updated the user to: " + userStatusRequest.getUserStatus()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UpdateResponse(false, "The user wasn't updated"));
        }
    }
}
