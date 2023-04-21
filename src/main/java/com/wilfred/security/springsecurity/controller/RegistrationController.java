package com.wilfred.security.springsecurity.controller;

import com.wilfred.security.springsecurity.payload.AuthenticationResponse;
import com.wilfred.security.springsecurity.payload.UserRequest;
import com.wilfred.security.springsecurity.payload.UserResponse;
import com.wilfred.security.springsecurity.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(registrationService.register(userRequest));
    }

}
