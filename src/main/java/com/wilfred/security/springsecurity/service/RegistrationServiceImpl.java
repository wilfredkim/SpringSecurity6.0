package com.wilfred.security.springsecurity.service;

import com.wilfred.security.springsecurity.listeners.OnRegistrationCompleteEvent;
import com.wilfred.security.springsecurity.model.Role;
import com.wilfred.security.springsecurity.model.Token;
import com.wilfred.security.springsecurity.model.User;
import com.wilfred.security.springsecurity.payload.AuthenticationResponse;
import com.wilfred.security.springsecurity.payload.UserRequest;
import com.wilfred.security.springsecurity.payload.UserResponse;
import com.wilfred.security.springsecurity.repository.RoleRepository;
import com.wilfred.security.springsecurity.repository.TokenRepository;
import com.wilfred.security.springsecurity.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserResponse register(UserRequest userRequest, HttpServletRequest request) {
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
        Role staffRole = roleRepository.findByName("ROLE_STAFF");
        user.setRoles(List.of(staffRole));
        user = userRepository.save(user);

        log.info("user created {}" + user.toString());

        // lets trigger mail sending here!!
        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        log.info("Sending emails>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("Sending emails>>>>>>>>>>>>>>>>>>>>>>>>>>" + appUrl);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));


        return UserResponse.builder().firstname(user.getFirstname())
                .lastname(user.getFirstname())
                .email(user.getFirstname())
                .dateCreated(user.getDateCreated())
                .dateUpdated(user.getDateUpdate()).build();
    }

    @Override
    public String validateVerificationToken(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByToken(token);
        log.info("Passed token ::::::::::>>  " + token);
        var isTokenValid = tokenOptional.map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
        if (isTokenValid) {
            Token userToken = tokenOptional.get();
            User user = userToken.getUser();
            if (user != null) {
                user.setAccountExpired(false);
                user.setAccountActivated(true);
                user.setAccountLocked(false);
                userRepository.save(user);
            } else {
                return "User with passed token not found!";
            }
        } else {
            return "Token is not valid!";
        }
        return "User validated!!";
    }
}
