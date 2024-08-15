package com.Meshal.PMS.Request;

import com.Meshal.PMS.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusRequest {
    private UserStatus userStatus;
    private int userId;
}
