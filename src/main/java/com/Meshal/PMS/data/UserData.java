package com.Meshal.PMS.data;

import com.Meshal.PMS.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
@NoArgsConstructor
public class UserData {
    private User user;
}
