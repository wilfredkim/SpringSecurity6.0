package com.wilfred.security.springsecurity.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
