package com.nyc.todoapp.config;

import com.nyc.todoapp.model.Role;
import com.nyc.todoapp.model.User;
import com.nyc.todoapp.repository.RoleRepository;
import com.nyc.todoapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Δημιουργία Ρόλων αν δεν υπάρχουν
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        // 2. ΕΛΕΓΧΟΣ: Δημιουργία χρήστη ΜΟΝΟ αν δεν υπάρχει ήδη
        // Αυτό αποτρέπει το σφάλμα "Duplicate entry" που είδαμε στα logs
        String defaultUsername = "Athina Alexopoulou";
        if (userRepository.findByUsername(defaultUsername).isEmpty()) {
            User user = new User();
            user.setUsername(defaultUsername);
            user.setPassword(passwordEncoder.encode("Sweet_Athina2005!"));
            user.setRoles(Collections.singleton(userRole));
            userRepository.save(user);
            System.out.println("Default user created: " + defaultUsername);
        } else {
            System.out.println("User '" + defaultUsername + "' already exists. Skipping creation.");
        }
    }
}