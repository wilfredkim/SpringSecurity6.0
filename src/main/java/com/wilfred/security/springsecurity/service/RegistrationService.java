package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.payload.AuthenticationResponse;
import com.wilfred.security.springsecurity.payload.UserRequest;
import com.wilfred.security.springsecurity.payload.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface RegistrationService {
    UserResponse register(UserRequest userRequest, HttpServletRequest request);

    String validateVerificationToken(String token);

}
