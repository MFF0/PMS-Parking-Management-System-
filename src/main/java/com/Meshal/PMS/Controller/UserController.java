package com.Meshal.PMS.Controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.Repositories.VehicleRepository;
import com.Meshal.PMS.Request.RegisterRequest;
import com.Meshal.PMS.Response.UpdateResponse;
import com.Meshal.PMS.data.UserData;
import com.Meshal.PMS.domain.User;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.UserService;
import com.Meshal.PMS.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserData userData;
    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        return userService.Register(registerRequest);
    }

    @DeleteMapping("/delete")
    @AuthorizedPMS
    public String deleteUser() {
        User user = userData.getUser();
        vehicleService.deleteAllUserVehicles();
        userRepository.deleteById(user.getUserId());
        return "User " + user.getFirstName() + " and associated vehicles deleted";
    }
    @AuthorizedPMS
    @PutMapping("/update")
    public UpdateResponse updateUser(@RequestBody RegisterRequest updatedUser) {

        Optional<User> existingUser = userService.optionalFindUser();

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(BCrypt.withDefaults().hashToString(12, updatedUser.getPassword().toCharArray()));
            user.setDateOfBirth(updatedUser.getDob());
            user.setModifiedDate(new Date());
            User savedUser = userRepository.save(user);

            return new UpdateResponse("Successfully updated the user "
                    + savedUser.getFirstName());
        } else {
            return new UpdateResponse("User not found");
        }
    }
}
