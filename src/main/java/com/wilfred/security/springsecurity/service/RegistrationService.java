package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.payload.AuthenticationResponse;
import com.wilfred.security.springsecurity.payload.UserRequest;
import com.wilfred.security.springsecurity.payload.UserResponse;

public interface RegistrationService {
    UserResponse register(UserRequest userRequest);

}
