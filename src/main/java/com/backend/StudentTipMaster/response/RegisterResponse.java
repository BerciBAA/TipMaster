package com.backend.StudentTipMaster.response;


import com.backend.StudentTipMaster.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String username;
    private String email;
    private Set<String> roles;
}
