package com.Meshal.PMS.security;


import com.Meshal.PMS.Repositories.UserRepository;
import com.Meshal.PMS.data.UserData;
import com.Meshal.PMS.domain.User;
import com.Meshal.PMS.exceptions.UnauthorizedException;
import com.Meshal.PMS.exceptions.UserNotFoundException;
import com.Meshal.PMS.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Slf4j
@Component
@Aspect

public class AuthorizedPMSHandler {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserData userData;

    @Before(" @annotation(com.Meshal.PMS.security.AuthorizedPMS)")
    public void monitor(JoinPoint ignoredJp) {

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid Authorization header");  // Change exception type if needed
        }

        final String token = authHeader.substring(7);
        final String userEmail = jwtService.extractEmail(token);
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        } else if (!jwtService.validateToken(token, user)) {
            throw new UserNotFoundException("Invalid token");
        } else {
            userData.setUser(user);
        }
    }
}
