package com.Meshal.PMS.Controller;

import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.Request.RegisterRequest;
import com.Meshal.PMS.Request.UserStatusRequest;
import com.Meshal.PMS.Response.GeneralResponse;
import com.Meshal.PMS.Response.UpdateResponse;
import com.Meshal.PMS.data.UserData;
import com.Meshal.PMS.domain.User;
import com.Meshal.PMS.security.AuthorizedPMS;
import com.Meshal.PMS.service.UserService;
import com.Meshal.PMS.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserData userData;
    private final VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new GeneralResponse(true, userService.Register(registerRequest)));
    }

    @DeleteMapping("/delete")
    @AuthorizedPMS
    public ResponseEntity<?> deleteUser() {
        User user = userData.getUser();
        vehicleService.deleteAllUserVehicles();
        userRepository.deleteById(user.getUserId());
        return ResponseEntity.status(HttpStatus.OK).
                body("User " + user.getFirstName() + " and associated vehicles deleted");
    }

    @AuthorizedPMS
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody RegisterRequest updatedUser) {
        userService.updateUser(updatedUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UpdateResponse(true, "User updated"));
        }


    @PutMapping("/updateUserStatus")
    public ResponseEntity<?> updateUserStatus(@RequestBody UserStatusRequest userStatusRequest) {
        log.info("{}-fxdjfnsdkjgnsdkjfsdfsf********{}", userStatusRequest.toString(), userStatusRequest.getUserId());
        return userService.updateUserStatus(userStatusRequest);
    }
}
