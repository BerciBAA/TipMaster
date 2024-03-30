package com.backend.StudentTipMaster.config;

import com.backend.StudentTipMaster.entity.Role;
import com.backend.StudentTipMaster.entity.User;
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
        admin.setEmail("admin@admin.hu");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setEmail("user@user.hu");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(Set.of(userRole));
        userRepository.save(user);

        User userAdmin = new User();
        userAdmin.setUsername("useradmin");
        userAdmin.setEmail("useradmin@useradmin.hu");
        userAdmin.setPassword(passwordEncoder.encode("useradmin"));
        userAdmin.setRoles(Set.of(userRole, adminRole));
        userRepository.save(userAdmin);
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
