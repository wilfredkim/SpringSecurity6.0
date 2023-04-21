package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.model.Role;
import com.wilfred.security.springsecurity.model.User;
import com.wilfred.security.springsecurity.payload.AuthenticationResponse;
import com.wilfred.security.springsecurity.payload.UserRequest;
import com.wilfred.security.springsecurity.payload.UserResponse;
import com.wilfred.security.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserResponse register(UserRequest userRequest) {
        var user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setDateCreated(LocalDateTime.now());
        user.setDateUpdate(LocalDateTime.now());
        user.setAccountActivated(false);
        user.setAccountLocked(true);
        user.setAccountExpired(false);
        user = userRepository.save(user);

        log.info("user created {}"+ user.toString());

        // lets trigger mail sending here!!

        return UserResponse.builder().firstname(user.getFirstname())
                .lastname(user.getFirstname())
                .email(user.getFirstname())
                .dateCreated(user.getDateCreated())
                .dateUpdated(user.getDateUpdate()).build();
    }
}
