package com.backend.StudentTipMaster.config;

import com.backend.StudentTipMaster.entity.Credential;
import com.backend.StudentTipMaster.entity.Role;
import com.backend.StudentTipMaster.entity.User;
import com.backend.StudentTipMaster.repository.CredentialRepository;
import com.backend.StudentTipMaster.repository.RoleRepository;
import com.backend.StudentTipMaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CredentialRepository credentialRepository;

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
    }

    private void initUsers(){

        Role userRole = roleRepository.findByRole("USER").orElseThrow(
                () -> new IllegalArgumentException("Role USER not found")
        );

        Role adminRole = roleRepository.findByRole("ADMIN").orElseThrow(
                () -> new IllegalArgumentException("Role ADMIN not found")
        );

        User admin = new User();
        admin.setUsername("admin");
        admin.setCredential(new Credential());
        admin.getCredential().setEmail("admin@admin.hu");
        admin.getCredential().setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(adminRole));
        credentialRepository.save(admin.getCredential());
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setCredential(new Credential());
        user.getCredential().setEmail("user@user.hu");
        user.getCredential().setPassword(passwordEncoder.encode("user"));
        user.setRoles(Set.of(userRole));
        credentialRepository.save(user.getCredential());
        userRepository.save(user);

        User userAdmin = new User();
        userAdmin.setUsername("useradmin");
        userAdmin.setCredential(new Credential());
        userAdmin.getCredential().setEmail("useradmin@useradmin.hu");
        userAdmin.getCredential().setPassword(passwordEncoder.encode("useradmin"));
        userAdmin.setRoles(Set.of(userRole, adminRole));
        credentialRepository.save(userAdmin.getCredential());
        userRepository.save(userAdmin);

        User temporaryUser = new User();
        temporaryUser.setUsername("temporaryUser");
        userRepository.save(temporaryUser);

    }

    private void initRoles(){

        Role userRole = new Role();
        userRole.setRole("USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleRepository.save(adminRole);
    }
}
