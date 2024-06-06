package com.backend.StudentTipMaster.mapper;

import com.backend.StudentTipMaster.config.CustomUserDetails;
import com.backend.StudentTipMaster.entity.Credential;
import com.backend.StudentTipMaster.entity.Role;
import com.backend.StudentTipMaster.entity.User;
import com.backend.StudentTipMaster.repository.CredentialRepository;
import com.backend.StudentTipMaster.repository.RoleRepository;
import com.backend.StudentTipMaster.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class RegisterRequestToUser implements Converter<RegisterRequest, User> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;

    public User convert(MappingContext<RegisterRequest, User> mappingContext) {
        RegisterRequest source = mappingContext.getSource();
        User destination = new User();
        destination.setUsername(source.getUsername());
        destination.setCredential(new Credential());;
        credentialRepository.save(destination.getCredential());
        destination.getCredential().setEmail(source.getEmail());
        destination.getCredential().setPassword(passwordEncoder.encode(source.getPassword()));

        Role role = roleRepository.findByRole("USER").orElseThrow(
                () -> new IllegalArgumentException("Role USER not found")
        );
        destination.setRoles(Set.of(role));

        return destination;
    }
}
