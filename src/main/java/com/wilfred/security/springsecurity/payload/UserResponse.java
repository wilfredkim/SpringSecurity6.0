package com.wilfred.security.springsecurity.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
