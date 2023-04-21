package com.wilfred.security.springsecurity.Util;
import com.wilfred.security.springsecurity.payload.UserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRequest user = (UserRequest) o;
        return user.getPassword().equals(user.getMatchingPassword());
    }

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
}
