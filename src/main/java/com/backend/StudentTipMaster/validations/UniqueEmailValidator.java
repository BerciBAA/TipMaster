package com.backend.StudentTipMaster.validations;

import com.backend.StudentTipMaster.repository.CredentialRepository;
import com.backend.StudentTipMaster.repository.UserRepository;
import com.backend.StudentTipMaster.validations.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final CredentialRepository credentialRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return true;
        return credentialRepository.findByEmail(email).isEmpty();
    }
}
