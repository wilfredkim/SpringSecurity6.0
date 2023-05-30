package com.wilfred.security.springsecurity.payload;

import com.wilfred.security.springsecurity.Util.PasswordMatches;
import com.wilfred.security.springsecurity.Util.ValidEmail;
import com.wilfred.security.springsecurity.Util.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatches
public class UserRequest {
    private String firstname;
    private String lastname;
    @ValidEmail
    private String email;
    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    @NotNull
    @NotEmpty
    @ValidPassword
    private String matchingPassword;


}
