package com.backend.StudentTipMaster.validations;

import com.backend.StudentTipMaster.repository.UserRepository;
import com.backend.StudentTipMaster.validations.annotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) return true;
        return userRepository.findByUsername(username).isEmpty();
    }
}
