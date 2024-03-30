package com.backend.StudentTipMaster.request;

import com.backend.StudentTipMaster.validations.annotations.UniqueEmail;
import com.backend.StudentTipMaster.validations.annotations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @UniqueUsername
    @NotBlank(message = "A felhasználónév nem lehet üres.")
    private String username;
    @UniqueEmail
    @NotBlank(message = "Az email cím nem lehet üres.")
    @Email(message = "Érvénytelen email cím formátum.")
    private String email;
    @NotBlank(message = "A jelszó nem lehet üres.")
    private String password;
}
