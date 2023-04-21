package com.wilfred.security.springsecurity.payload;

import com.wilfred.security.springsecurity.Util.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@PasswordMatches
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String matchingPassword;
}
