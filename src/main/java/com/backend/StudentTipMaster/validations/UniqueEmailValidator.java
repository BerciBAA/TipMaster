package com.backend.StudentTipMaster.validations;

import com.backend.StudentTipMaster.repository.UserRepository;
import com.backend.StudentTipMaster.validations.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return true;
        return userRepository.findByEmail(email).isEmpty();
    }
}
